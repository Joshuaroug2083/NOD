# AGENTS.md — Permanent Engineering Rules for Nod

These instructions apply to all coding agents working in this repository. Treat them as durable project constraints unless a human explicitly changes them.

## 1. Product invariants

1. Nod v1 is **Android only**.
2. Nod v1 is **two-device only**.
3. Nod must function without public internet for its core sharing flow.
4. Nod v1 has **no required Nod account and no Nod backend**.
5. A user's Nod Profile is local to the device and must not require cloud identity.
6. The receiver must explicitly approve incoming content before payload transfer begins.
7. Device connection approval and content approval are separate consent events.
8. Discovery must not permanently broadcast the user's identity by default.
9. The Nod brand, logo, name, and core visual identity are locked. Do not redesign them.
10. Do not silently change product decisions to simplify implementation. Document conflicts instead.

## 2. Platform and stack

- Kotlin.
- Jetpack Compose + Material 3.
- Coroutines, Flow, StateFlow.
- Hilt + KSP.
- Room for structured local metadata.
- Proto DataStore for non-sensitive settings.
- Protocol Buffers for the device-to-device control protocol.
- Android Keystore + AES-GCM for sensitive local profile encryption.
- Nearby Connections `P2P_POINT_TO_POINT` is the first transport implementation.
- Transport must be abstracted behind `NodTransport`.
- CameraX + bundled ML Kit Text Recognition for number scanning.
- libphonenumber for number parsing/normalization.
- SensorManager for Shake to Nod.
- Foreground Service only where Android background constraints require it.
- TileService for Quick Settings launch.
- MediaStore / Storage Access Framework / Contacts APIs for user data integration.
- WorkManager may be used for deferred cleanup/maintenance, never as the live transfer engine.

## 3. Android configuration

Initial baseline:

- `minSdk = 26`
- `targetSdk = 36`
- `compileSdk = 36`
- Java toolchain 17

If Android/Play requirements have changed, do not silently bump these values. Document the incompatibility and proposed change in an ADR before modifying the baseline.

## 4. Architecture rules

- UI must not depend directly on Nearby Connections, Room, SensorManager, MediaStore, or ContactsProvider.
- Domain code must not depend on Android UI classes.
- `NodTransport` is the boundary between product logic and transport technology.
- The first transport adapter is `NearbyNodTransport`.
- Wi-Fi Direct is a future adapter, not an excuse to contaminate higher layers with transport-specific logic.
- Session state is explicit and modeled as a finite state machine.
- Protocol state is explicit and versioned.
- File transfer metadata and file bytes are separate planes.
- Use Protobuf BYTES/control messages for session/control traffic; file payloads remain separate.
- Do not assume payload arrival order across payload types. Correlate using explicit IDs.
- No global mutable singleton state outside dependency injection and platform-required service lifecycle boundaries.

## 5. Security and privacy rules

- Never advertise a phone number, email address, complete Nod Profile, or sensitive contact fields during discovery.
- Discovery identity must be a temporary endpoint identifier plus a user-configurable nearby alias.
- First-time session establishment requires mutual verification of the same authentication code/token.
- A connection is not trusted until both endpoints explicitly accept verification.
- Incoming content must not begin transferring until the receiver accepts a manifest.
- Sensitive local profile fields are encrypted at rest.
- Keystore key material must not be exportable.
- Exclude sensitive Nod profile data and cryptographic material from automatic cloud backup.
- Never log contact content, filenames if avoidable, email addresses, phone numbers, file contents, encryption keys, authentication tokens, or raw OCR images.
- Received files are staged in private temporary storage and become final only after successful verification.
- SHA-256 mismatch is a hard failure; do not expose the file as complete.
- Never auto-open received executable or document content.
- Cancellation, crash, disconnect, low-storage, and integrity failure must clean up partial transfer state safely.

## 6. Quick Nod rules

- "Always Ready" means the optional gesture/background readiness mechanism is active; it does **not** mean permanent discovery.
- Shake ×3 and Quick Settings Tile must invoke one shared use case: `StartQuickNodSession`.
- Quick Nod discovery/advertising is time-bounded; default target is 60 seconds unless changed by product decision.
- Never bypass the Android lock screen.
- Do not attempt forbidden background Activity launches. Use Android-approved notifications/actions when the app cannot surface UI directly.
- Shake detection must include false-positive protection and cooldown.

## 7. File-transfer correctness rules

Before a file transfer:

1. sender creates a transfer manifest;
2. receiver sees type/count/size information;
3. receiver approves;
4. receiver verifies sufficient storage;
5. bytes transfer into private temporary storage;
6. progress is computed from actual bytes transferred;
7. integrity is verified;
8. final destination write/move is atomic where possible;
9. completion acknowledgement is exchanged.

Never overwrite an existing user file silently.

## 8. Code-quality rules

- Prefer small explicit types over primitive strings/booleans for IDs and states.
- Favor immutable models.
- Use sealed interfaces/classes for session and error states.
- Public interfaces require KDoc when behavior is non-obvious.
- No swallowed exceptions.
- No `GlobalScope`.
- No blocking I/O on the main thread.
- No entire-file reads into memory for production transfers.
- Timeouts must be explicit and testable.
- Random IDs use a cryptographically suitable generator where security matters.
- Time must be injected or wrapped for deterministic tests when used in domain logic.

## 9. Testing rules

Every behavior-changing PR must include appropriate tests.

At minimum:

- domain state machine tests;
- protocol encode/decode compatibility tests;
- transfer lifecycle tests;
- integrity failure tests;
- cancellation tests;
- low-storage/error-path tests;
- UI state tests where logic is presented to users.

The transport spike must be tested on physical devices. Emulator-only validation is insufficient.

## 10. Agent operating procedure

Before implementing a substantial change:

1. read relevant ADRs and architecture docs;
2. identify affected invariants;
3. state assumptions;
4. implement the smallest coherent vertical slice;
5. run tests/lint/build;
6. summarize changed files, known limitations, and next risks.

If documentation and code disagree, do not guess. Surface the contradiction and propose an ADR/document update.
