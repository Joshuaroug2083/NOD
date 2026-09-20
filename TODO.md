# Nod implementation tracker

Updated: 20 September 2026. **Current phase: 0 — foundation validation.** The app is a minimal diagnostic shell. Discovery, pairing and transfers are not implemented yet.

**Production UI status: no production product screens are implemented or functioning yet.** The named images in `Nod_All_Screens/` are design references. Track each screen's design, UI implementation, feature integration and validation separately in [the screen status table](docs/design/SCREEN_IMPLEMENTATION_STATUS.md).

This is the working checklist for tracking implementation, not a replacement product specification. Follow the [canonical implementation plan](Nod_Codex_Engineering_Handoff/IMPLEMENTATION_PLAN.md), [Phase 0/1 backlog](Nod_Codex_Engineering_Handoff/PHASE_0_1_BACKLOG.md), [permanent rules](AGENTS.md), [API review](docs/architecture/API_REVIEW.md) and [updated UI register](docs/design/UI_REFERENCE.md). The latest own-identity-only correction in [ADR-011](docs/adrs/ADR-011-own-identity-only.md) overrides older contact-browser tasks.

## How to read progress

- `[x]` means the exact deliverable or check described is complete. Implementing a component and validating it are separate checkboxes.
- `[ ]` means planned, in progress, unverified or gated; the text identifies which.
- A phase exits only when its required validation passes. An APK, screenshot or emulator test does not prove two-phone radio behavior.
- Record commands, device/API versions, results and limitations in [implementation evidence](docs/engineering/STATUS.md). Attach a preview at each milestone; preserve screenshots and test reports that justify completion.
- Tasks with `NOD-0001` through `NOD-0120` retain the handoff IDs. Later IDs below are local tracking labels, not additions to the product scope.
- Update this file and the evidence log after each meaningful implementation milestone. Do not use a simple checkbox percentage as an estimate of remaining effort.

## Milestone dashboard

| Milestone | Status | Preview / evidence required |
|---|---|---|
| M0 — Buildable foundation | Builds/quality/clean clone passed; preview captured; runtime/IDE validation open | [Actual shell preview](docs/engineering/previews/phase0-shell.png), debug APK; instrumentation and compatible IDE import still open |
| M1 — Verified two-phone file exchange | Not started; M0 gate | Actual two-phone demonstration: verification, consent, byte progress, hash verification, completion and disconnect |
| M2 — Reliable production transfer engine | Not started; M1 gate | Transfer/recovery demonstration and integrity/cleanup test report |
| M3 — Private own-card sharing | Not started; M2 gate | Sender field review, receiver offer/preview and explicit system contact save |
| M4 — Quick Nod | Not started | Manual/tile/shake countdown and optional readiness demonstration on real devices |
| M5 — Number scanner | Not started | Offline camera/gallery recognition, correction and copy demonstration |
| M6 — Approved production interface | Not started; reliable core required | Screens mapped to approved references, complete flows and accessibility review |
| M7 — Release candidate | Not started | Device/security/privacy/release evidence, signed internal candidate and release checklist |

## Phase 0 — Repository and build foundation

### Source review and engineering decisions

- [x] Read the imported documentation, complete engineering handoff and available planning conversation content; record retrieval limits rather than claiming missing images were inspected.
- [x] Inventory and hash the 127 original source assets; preserve their contents unchanged.
- [x] Record the exact initial module structure and implementation sequence in [the Phase 0 mapping](docs/architecture/IMPLEMENTATION_PLAN.md).
- [x] Record current Android/API contradictions and feasibility gates without changing locked decisions.
- [x] Retain the primary 18-screen board, locked logo and retrieved supporting attachments with provenance.
- [x] Register updated screens 19–56 and explicitly retire address-book browsing/redistribution.
- [x] Inspect the newly supplied `Nod_All_Screens` folder, README and contact sheet; inventory all 53 files and inspect full-resolution additions 19–27. Record screen 11 as retired and screen 28 onward as incomplete/draft rather than missing everything after 18.
- [x] Add permanent engineering rules in root `AGENTS.md`.
- [x] Add this detailed tracker and the architecture decision index (NOD-0009).

### Repository, modules and toolchain

- [x] Initialize local Git and exclude SDK paths, build output, inspection files, local tools and signing material.
- [x] Add Gradle Kotlin DSL, pinned version catalog and checksum-pinned wrapper (NOD-0002).
- [x] Configure Java 17 and minSdk 26 / compileSdk 36 / targetSdk 36 (NOD-0001).
- [x] Document temporary `dev.nod.spike` application ID, with `.debug` suffix; final public package ownership remains undecided.
- [x] Create `app`, `core:model`, `core:common`, `core:protocol`, `core:testing`, `domain`, `transport:api`, `transport:nearby` and `feature:spike` (NOD-0007).
- [x] Wire Compose/Material 3, Coroutines/StateFlow and a plain diagnostic shell without simulated feature behavior.
- [x] Wire Hilt/KSP and demonstrate an injectable dependency with a fake in a ViewModel test (NOD-0005).
- [x] Configure Protobuf lite generation and initial versioned HELLO envelope; retain canonical field numbers (NOD-0006).
- [x] Configure debug and unsigned, shrunk release variants; no signing keys in source.
- [x] Disable backups and add Android 12+ cloud/device-transfer extraction exclusions.
- [x] Package an unchanged locked logo in the launcher container and document provenance/limited themed-icon exception.

### Quality setup and exit checks

- [x] Configure Android lint, ktlint and detekt through `qualityCheck` (NOD-0003).
- [x] Configure JUnit, coroutine tests, Turbine and Compose instrumentation (NOD-0004).
- [x] Add HELLO round-trip and unknown additive field tests, plus the ViewModel state test.
- [x] Run the three JVM tests successfully (two protocol tests and one ViewModel test); aggregate validation remains a separate gate below.
- [x] Add a Compose shell-rendering instrumentation test.
- [x] Push the foundation to the user-specified GitHub repository, `Joshuaroug2083/NOD`, on `main`.
- [x] Add GitHub Actions build/test/static-analysis workflow and hosted API 26/36 Compose test jobs (NOD-0008).
- [ ] Pass the hosted workflow. The first run failed during Android SDK setup before any build or test; retain the failure and verify the correction.
- [x] Document local build commands, dependencies/licenses and original source review (NOD-0009).
- [x] Pass aggregate unit tests, ktlint, detekt and Android lint; app lint reports 0 errors, 0 warnings and 20 informational dependency-update hints.
- [x] Produce the installable debug shell APK.
- [x] Deliver `build/distributions/Nod-0.0.1-phase0-debug.apk` for the user's phone test; debug build and APK signature verification pass. This validates packaging, not successful installation on that phone.
- [x] Produce the Compose instrumentation APK; device execution has been attempted but has not passed.
- [x] Produce the shrunk unsigned release APK with final validation; combined quality/debug/release/instrumentation build passed.
- [x] Validate a clean source copy offline without copied project outputs/local settings and with task-output cache disabled; build passed in 4m 52s, with a Windows compiler file-lock fallback recorded in evidence.
- [x] Record literal clean-clone build evidence: local checkpoint `b077090`, branch `codex/phase0-foundation`; offline build with task-output caching disabled passed in 2m 55s (98 tasks executed). Both original and screen-package hash inventories match in the clone.
- [ ] Confirm project imports/syncs in compatible Android Studio (NOD-0001). Installed 2025.1.2 supports AGP through 8.12; Nod uses 8.13.2 and needs 2025.1.3 or newer compatible Studio. CLI builds alone do not prove IDE sync.
- [x] Install and launch the shell on the task-local API 36 emulator; capture its actual rendered screen.
- [ ] Pass the Compose instrumentation test. Both attempts returned `Process crashed`; the second is confirmed as a process-start ANR, with heavy guest CPU/memory pressure and system-process failures. No assertion result or app-code correctness claim. See [device evidence](docs/engineering/DEVICE_VALIDATION.md); rerun on a stable emulator/physical device.
- [x] Align library instrumentation target SDK with app target 36; regenerated manifest inspected. Reduce Gradle to a 1536 MB heap/two workers with in-process Kotlin compilation; aggregate validation passes.
- [x] **Foundation build preview:** capture [the actual shell](docs/engineering/previews/phase0-shell.png) and provide the built debug APK, clearly labeled as the foundation. Full M0 exit remains pending.
- [ ] Record all checks and any remaining exit limitations in `STATUS.md`; only then declare Phase 0 exited.

## Phase 1 — Two-phone transport proof of concept

Use a plain technical UI. Preserve production UI work for Phase 6. Complete NOD-0100–0120 on the selected Nearby adapter before making performance or interoperability claims.

### Transport and session foundation

- [ ] NOD-0100: Define typed `PeerId`, `EndpointAlias`, `ConnectionRequestId`, `TransportPayloadId`, events and errors without Android/Google SDK types.
- [ ] NOD-0101: Define `NodTransport` lifecycle, discovery, connection, verification, payload, cancellation and disconnect contracts.
- [ ] NOD-0102: Implement Nearby `ConnectionsClient` inside `transport:nearby`, using `P2P_POINT_TO_POINT` and one remote peer only.
- [ ] Map callbacks into bounded Flow/event channels; terminal and consent events must not be dropped with progress updates.
- [ ] Detect GMS availability, required permissions and radio readiness; support explicit denial/unavailable recovery.
- [ ] NOD-0103: Add start/stop advertising controls and alias/status to the technical harness.
- [ ] NOD-0104: Add start/stop discovery, peer list and connection request action.
- [ ] Stop discovery/advertising at the appropriate connection transition and prevent a second peer from joining.
- [ ] NOD-0105: Show the same transport authentication code on both phones and require explicit approval from each person.
- [ ] Reject mismatch/rejection/timeout and clean up; do not remember approval as a future trust bypass.
- [ ] NOD-0106: Serialize session transitions through idle, advertising/discovering, pending, verifying, connected, manifest pending, transferring, payload verification and terminal states.
- [ ] Test late callbacks, duplicate events, stale sessions and cancellation races with injectable clocks/dispatchers.

### Versioned control protocol and consent

- [ ] NOD-0107: Extend the canonical versioned Protobuf envelope with session/message identifiers and required body variants.
- [ ] NOD-0108: Exchange HELLO versions/capabilities; reject unsupported versions and unknown required capabilities clearly.
- [ ] Bound control message size, identifiers, aliases, filenames, item counts and total bytes; reject malformed/overflowing values.
- [ ] Validate message order and session association; reject stale, duplicate and replayed control messages.
- [ ] NOD-0109: Create a manifest containing transfer ID, item ID, display name, MIME type, exact 64-bit size, SHA-256 and checked total bytes.
- [ ] NOD-0110: Show the receiver the manifest summary and require explicit acceptance for that exact transfer/session.
- [ ] Verify rejection, expired consent, changed manifest and wrong-session acceptance never start file content transmission.

### File safety and actual transfer

- [ ] **Feasibility gate:** test where supported Nearby FILE reception actually stages data on the API/OEM matrix before real user content is transferred.
- [ ] Reconcile private staging and no-visible-incomplete-file requirements with observed FILE behavior. If incompatible, record evidence and a product decision before changing payload strategy or transport.
- [ ] NOD-0111: Implement private staging ownership, capacity checks and deterministic cancellation/restart cleanup.
- [ ] NOD-0112: Generate/select a large test file, stream through file payloads with bounded memory and show actual transferred/total bytes on both phones.
- [ ] NOD-0113: Correlate transfer ID, item ID and transport payload ID explicitly; tolerate cross-type arrival order.
- [ ] NOD-0114: Verify received length and SHA-256 off the main thread after complete receipt; transport SUCCESS alone must not complete Nod.
- [ ] NOD-0115: Finalize only verified content, using a private same-filesystem publication strategy for the spike and safe duplicate-name handling.
- [ ] Fail closed on storage write/finalization error; incomplete or corrupt files never become completed output.
- [ ] NOD-0116: Exchange the documented completion acknowledgement; sender completion follows the defined ACK policy.
- [ ] Represent lost acknowledgement as uncertain remote outcome; preserve data already finalized by the receiver.
- [ ] NOD-0117: Implement sender/receiver cancellation, peer reason/category and cleanup without swallowing coroutine cancellation.
- [ ] NOD-0118: Handle clean disconnect, range loss and process death with honest terminal states and restart cleanup.

### Mandatory negative scenarios and physical evidence

- [ ] Test connection rejection and authentication mismatch.
- [ ] Test manifest rejection, sender cancellation and receiver cancellation.
- [ ] Test backgrounding and killing either app during transfer.
- [ ] Test disabling Wi-Fi/Bluetooth and moving out of range.
- [ ] Test insufficient storage, intentional SHA-256 mismatch and duplicate destination filename.
- [ ] Test protocol version mismatch and malformed/out-of-order control messages.
- [ ] Test late payload callbacks and ACK loss without false success or deleting finalized files.
- [ ] NOD-0119: Complete a large-file exchange on at least two distinct physical Android device families; record models, APIs, sizes, timing, hashes and outcomes.
- [ ] Verify every forced interruption leaves no corrupt/partial file labeled complete and a sane terminal session state.
- [ ] NOD-0120: Publish spike report: setup latency, throughput, permissions, OEM differences, failure modes, Nearby decision and resume feasibility.
- [ ] **M1 preview:** show the real two-phone flow from advertise/discover through code approval, consent, byte progress, SHA-256, finalization, ACK and disconnect.
- [ ] Exit Phase 1 only with the required hardware evidence; emulator success cannot satisfy this gate.

## Phase 2 — Production protocol and transfer engine

- [ ] P2-01: Finalize protocol v1 documentation/schema; define compatibility, required capabilities and removed-field reservations.
- [ ] P2-02: Harden envelope/body validation and deterministic session/transfer transition rules with negative tests.
- [ ] P2-03: Define transfer/item/payload ownership and reconciliation for interleaved callbacks and repeated terminal events.
- [ ] P2-04: Implement safe staging/publication abstractions for private storage and supported MediaStore/SAF paths; explicitly handle API 26–28 provider limitations.
- [ ] P2-05: Persist minimum transfer/history metadata in Room; add schema export and migration tests with the first database.
- [ ] P2-06: Add crash/restart cleanup and deferred WorkManager maintenance; live transfer stays outside WorkManager.
- [ ] P2-07: Implement capacity limits, quota/retention policy, filenames, MIME handling and safe duplicate destinations.
- [ ] P2-08: Finalize timeout, cancellation, peer loss, partial batch and acknowledgement-uncertainty behavior.
- [ ] P2-09: Build bounded-memory, large-file and multiple-item tests; use 64-bit progress and checked arithmetic throughout.
- [ ] P2-10: Add local-only reliability diagnostics with redacted logs; no analytics SDK or remote telemetry.
- [ ] P2-11: Confirm no automatic opening/execution of received files and no silent overwrite of user data.
- [ ] P2-12: Decide resume only from measured feasibility and an explicit capability design; retain safe restart otherwise.
- [ ] **M2 preview:** demonstrate verified multi-item transfer, cancellation, interruption cleanup and honest uncertain outcomes.

## Phase 3 — Encrypted Nod Profile and own-card exchange

This phase follows ADR-011: only the user's own identity/business card and files are shareable. Older handoff tasks to browse/select other Android Contacts are superseded.

- [ ] P3-01: Define own-profile fields, validation and Quick/Personal/Professional share presets; keep nearby alias separate.
- [ ] P3-02: Implement Keystore-backed AES-GCM with unique IVs and authenticated storage; define key loss/invalidation recovery.
- [ ] P3-03: Store profile/preferences using the agreed encrypted storage design and Proto DataStore; add migration/corruption tests as schemas appear.
- [ ] P3-04: Ensure profile fields/photos do not enter plaintext logs, backups, discovery metadata or unsolicited offers.
- [ ] P3-05: Implement local profile creation/editing and preset field selection through ViewModels/use cases.
- [ ] P3-06: Require sender review of exact selected own-card fields before offering them.
- [ ] P3-07: Offer permitted metadata/field labels first; transmit private values/photo only after receiver acceptance.
- [ ] P3-08: Extend and validate the versioned protocol for accepted card content with explicit size/field limits.
- [ ] P3-09: Preview received card content after successful receipt; save only through an explicit Android contact insertion flow.
- [ ] P3-10: Define duplicate-contact UX using permitted system flows; no broad Contacts read, silent merge or shadow address book.
- [ ] P3-11: Verify independent consent for each direction of two-way sharing.
- [ ] P3-12: Test encryption, invalid ciphertext/key states, backups/device transfer, field selection and pre-consent disclosure prevention.
- [ ] **M3 preview:** show own-profile editing, outgoing field review, incoming offer, received preview and explicit system save.

## Phase 4 — Quick Nod and optional background readiness

- [ ] P4-01: Implement one `StartQuickNodSession` command used by manual launch, tile and enabled shake triggers.
- [ ] P4-02: Enforce default 60-second visibility using an injectable monotonic clock; stop on expiry, cancellation and connection transition.
- [ ] P4-03: Test overlapping triggers, expired windows, process/lifecycle changes and no permanent discovery.
- [ ] P4-04: Implement first-class Quick Settings TileService with API-appropriate PendingIntent/launch handling.
- [ ] P4-05: Implement optional SensorManager three-shake recognition; calibrate threshold, cooldown, false positives and battery use.
- [ ] P4-06: Resolve the permitted foreground-service type/start behavior for optional Always Ready; document evidence before implementation.
- [ ] P4-07: Implement opt-in sensor readiness, notification/actions and stop control; readiness must not continuously advertise/discover.
- [ ] P4-08: Respect notification permissions, background launch limits and lock-screen authentication; no forced unlock or hidden activity launch.
- [ ] P4-09: Persist user preferences with Proto DataStore and explain disabled/unsupported states honestly.
- [ ] P4-10: Test supported APIs and OEM battery restrictions on hardware; report limitations without promising universal behavior.
- [ ] **M4 preview:** demonstrate manual/tile/shake entry, visible countdown, automatic expiry and optional readiness controls.

## Phase 5 — Offline number scanner

- [ ] P5-01: Add CameraX and context-specific camera permission handling.
- [ ] P5-02: Use bundled ML Kit Text Recognition with no recognition-time model download dependency.
- [ ] P5-03: Handle analyzer backpressure, frame closure, rotation, lifecycle and cancellation.
- [ ] P5-04: Extract candidate phone numbers and normalize using libphonenumber and explicit region handling.
- [ ] P5-05: Let the user review/edit recognition before copy or another explicit action; never silently trust OCR output.
- [ ] P5-06: Support gallery input through a system picker without broad photo-library access.
- [ ] P5-07: Benchmark printed/handwritten examples, lighting, scripts and ambiguity; report accuracy limitations from measurements.
- [ ] P5-08: Verify camera/gallery flows with connectivity unavailable, denied permission and invalid/no-number input.
- [ ] **M5 preview:** show real offline scan, candidate review/correction, copy and gallery recognition.

## Phase 6 — Production UI using the approved Nod design

- [ ] P6-01: Open the exact primary board and relevant source images before work; reconcile each screen with the current UI register and ADRs.
- [ ] P6-02: Use the available canonical 01–27 images in `Nod_All_Screens`; complete/correct screen 28 onward when its UI work begins. Quick Nod/exploratory images remain draft; do not label them approved by filename alone.
- [ ] P6-03: Implement Compose design tokens/components using locked logo, colors, typography, surfaces, spacing and rounded controls.
- [ ] P6-04: Review production launcher/splash asset clarity while preserving logo geometry/colors/proportions; keep originals and provenance.
- [ ] P6-05: Build onboarding, own-profile setup and contextual permissions; retire the old broad Allow All flow.
- [ ] P6-06: Build home, nearby discovery, peer request and explicit code verification; no unconsented profile disclosure.
- [ ] P6-07: Build own-share-preset selection/review, incoming card offer and explicit received-card save (chat 20–28).
- [ ] P6-08: Build file selection, incoming manifest/details and consent before transfer (chat 36–37).
- [ ] P6-09: Build byte progress, verification, finalized completion, transfer history/details and acknowledgement-uncertain state.
- [ ] P6-10: Build failure/retry/peer-unavailable/storage/integrity/permission recovery states (chat 38–46).
- [ ] P6-11: Build Quick Nod introduction/countdown, shake settings, tile setup and optional readiness states (chat 29–35 and 56).
- [ ] P6-12: Build scan camera/gallery/review, settings, alias, visibility, privacy and cleanup flows.
- [ ] P6-13: Implement block/resume/duplicate screens only to the capability actually proven and accepted; do not promise durable alias-based blocking.
- [ ] P6-14: Build About/legal with verified ownership/support details; do not publish policy drafts or invent missing details.
- [ ] P6-15: Verify TalkBack labels/order, touch targets, contrast, large fonts, keyboard/insets, rotation and reduced motion.
- [ ] P6-16: Test narrow/tall devices and API/OEM differences; retain immutable ViewModel state and domain boundaries.
- [ ] P6-17: Review each implemented flow beside its approved reference and capture screenshots; dark mode requires explicit product approval.
- [ ] **M6 preview:** present the complete branded journeys with actual functioning states, mapping changes back to approved screens.

## Phase 7 — Hardening and release readiness

- [ ] P7-01: Complete physical device/API matrix from Android 8 through the supported current target, including different OEM families.
- [ ] P7-02: Re-run permission, denial, radio, background, lock-screen, battery and no-GMS scenarios.
- [ ] P7-03: Complete threat-model/security review of consent, replay/order, path traversal, malformed inputs, crypto, integrity and cleanup.
- [ ] P7-04: Validate Room/Proto/protocol migration and recovery from storage corruption/key invalidation/process death.
- [ ] P7-05: Validate backup/device-transfer exclusions on the device matrix; verify no private profile data leaks in logs or exported artifacts.
- [ ] P7-06: Audit direct/transitive dependency provenance, licenses, notices, SDK behavior and release manifest; maintain dependency register.
- [ ] P7-07: Measure startup, transfer memory/throughput, battery, crash/ANR behavior and accessibility; add baseline profile/macrobenchmark where useful.
- [ ] P7-08: Finalize legally owned package name, owner/support details, reviewed policies and policy URLs; drafts remain unpublished until ready.
- [ ] P7-09: Verify Play Data Safety, foreground-service declarations and target API requirements against actual implementation.
- [ ] P7-10: Establish private signing/recovery process, release shrinker validation and reproducible signed candidate build.
- [ ] P7-11: Prepare store screenshots/listing text from real app behavior and approved branding; avoid unsupported superiority claims.
- [ ] P7-12: Complete release checklist and internal acceptance, with known limitations and rollback/incident process.
- [ ] **M7 preview:** provide the installable release candidate and evidence summary for final distribution review.

## Decisions and external evidence still needed

| Item | Needed before | Current constraint |
|---|---|---|
| Two distinct physical Android device families | Phase 1 exit | No phones attached during bootstrap; model/API and permission/radio outcomes must be recorded |
| Nearby FILE private staging behavior | Phase 1 real-content transfer | Supported API/Downloads behavior must be measured; no silent transport or payload substitution |
| Supported publication on API 26–28/SAF | Production file export | Provider atomicity/pending semantics cannot be assumed |
| Always Ready foreground-service legality/behavior | Phase 4 service work | `connectedDevice` does not automatically justify idle sensor listening |
| Resume and durable block identity | Related capability/UI | Explicit capability/security decisions needed; endpoint IDs and aliases are insufficient |
| Remaining generated UI images | Corresponding Phase 6 visual reviews | Named 01–27 images are local; the folder README says 28 needs correction and Quick Nod images are draft; 28–56 is not a completed approved set |
| Final package, owner, support and policies | Public release | Temporary namespace and unreviewed legal placeholders are not release assets |

## Current next actions

1. Diagnose/re-run the failed instrumentation test on a stable emulator or device; the shell launch and screenshot are captured.
2. Keep the screen-by-screen table clear: available design, implemented UI, connected feature and verified flow are distinct.
3. Verify import in compatible Android Studio; main builds, quality checks and literal clean-clone build have passed.
4. Begin Phase 1 only after the Phase 0 gate is satisfied; start with transport contracts and the private-staging feasibility experiment.
