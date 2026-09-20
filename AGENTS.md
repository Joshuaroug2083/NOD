# Nod permanent engineering rules

## Authority and scope
- Read `docs/README.md`, the complete `Nod_Codex_Engineering_Handoff/` (including its AGENTS.md, architecture, backlog, coding standards and ADRs), and relevant source specifications before changes. The supplied handoff is canonical; implementation notes supplement it, not replace it. Original controlled documents remain in `DOCX/` and `PDF/`.
- Follow the handoff phase numbering. Phase 0 has no feature behavior beyond a minimal shell. Phase 1 may begin only after Phase 0 exits cleanly unless the user explicitly authorizes otherwise.
- WorkManager is for deferred cleanup/maintenance, never live transfer. No mutable global state outside dependency injection/platform lifecycle ownership. Use typed IDs/errors and sealed states; document non-obvious public contracts with KDoc.
- The user's locked decisions and latest accepted product decisions take precedence over older mockups and examples. Record conflicts in `docs/architecture/API_REVIEW.md`; never silently change product scope.
- Latest explicit scope correction: Nod shares only the user's own local identity/business card and files. Never browse, manage, select or redistribute other people's contacts from the phone. Retire Select Contacts/My Contacts/address-book screens. Own-card presets remain Quick/Personal/Professional; saving a received card uses explicit Android system contact insertion. No broad Contacts read permission for the core product. See `docs/adrs/ADR-011-own-identity-only.md` and `docs/design/UI_REFERENCE.md`.
- Nod is native Android only, minSdk 26, Kotlin, Compose/Material 3, Coroutines/Flow/StateFlow, Hilt with KSP, Room, Proto DataStore and versioned Protocol Buffers. No accounts, backend, cloud sync, analytics SDK or remote transfer for v1.
- CameraX, bundled offline ML Kit Text Recognition, libphonenumber, SensorManager, optional foreground-service Always Ready, TileService, MediaStore/SAF and Android Contacts remain the selected production stack. Add them when their phase begins, not as unused dependencies.
- Complete and validate the two-device technical spike before polished UI work. Never describe an emulator/unit-test pass as proof of radio interoperability.

## Brand
- Primary UI reference, explicitly reaffirmed by the user: `ChatGPT Image Sep 19, 2026, 09_46_22 PM.png` at repository root. Always open and reference this exact image before production UI work. Preserve its visual design; add required verification/consent/Quick Nod states in the same language. The plain Phase 0 harness is not a replacement design.
- Also inspect `Nod_All_Screens/README.txt` and the relevant named screen asset before UI implementation. Its canonical 01–27 references, legacy screen 11 and exploratory/draft images are distinguished in `docs/design/SCREEN_IMPLEMENTATION_STATUS.md`. Image availability is not implementation or working-feature evidence. Screen 28 onward is not a complete approved asset set.
- Product: Nod. Tagline: Closer. Faster. Yours.
- `Brand/Nod_Locked_Logo_Reference.png` is authoritative. Do not redesign, approximate with text, distort, recolor or generatively replace it. Clarity-only derivatives must preserve exact geometry, colors and proportions; retain original and provenance.
- Approved UI tokens: lemon #BFFF00, white #FFFFFF, charcoal #171717, soft green #F4FFE6. Earlier SVGs are flow references, not authority for a replacement logo or conflicting colors.

## Architecture
- UI consumes immutable state through ViewModels/domain use cases. It must not call Nearby, Room, filesystem providers or contacts directly.
- Domain and transport API modules must not expose Android, Google Play services, Compose or provider-specific types.
- Nearby Connections P2P_POINT_TO_POINT is the primary adapter behind `NodTransport`. Exactly one remote peer per v1 session. Do not build Wi-Fi Direct until its phase is authorized; preserve the adapter boundary.
- Serialize session transitions. Reject stale, duplicate, out-of-session and out-of-order control messages. Use bounded queues and distinguish lossy progress updates from lossless consent/terminal events.
- Use structured concurrency, injectable dispatchers/clocks and explicit cancellation. Do not swallow cancellation or perform hashing/I/O on the main thread.

## Trust, protocol and transfer safety
- A peer alias is untrusted display text. Both users must compare the transport authentication code and explicitly approve each session. No automatic acceptance or remembered trust bypass in v1.
- Connection approval and content approval are separate. Do not send content before a valid receiver acceptance for that exact manifest and session.
- Every envelope is versioned and carries session/message identifiers. Negotiate capabilities. Reserve removed Protobuf field numbers; document breaking changes. Never use timestamps as a trust authority.
- Bound control size, field lengths, item count and total bytes. Reject unknown required capabilities, malformed manifests, arithmetic overflow, path traversal and invalid payload associations.
- Correlate transfer ID, item ID and transport payload ID explicitly; never assume ordering across payload types.
- Stream large files with bounded buffers and 64-bit byte counts. Verify exact length and SHA-256 of received staging data before publication. Transport SUCCESS is not Nod completion.
- Incomplete/unverified files must never appear as completed. Fail closed on hash mismatch, cancellation, storage failure, process death or peer loss. Cleanup must survive restart. Never overwrite existing user files silently.
- Completion requires local verified finalization and the documented peer acknowledgement sequence. ACK loss means an uncertain remote outcome, not an invented success or deletion of already finalized user data.
- No automatic opening/execution of received files. Contacts are saved only by explicit user action; no shadow address book.

## Privacy and Android constraints
- Encrypt sensitive Nod Profile fields with Android Keystore-backed AES-GCM and unique IVs. No plaintext profile logs/exports/backups. Disable/exclude cloud and device-transfer backup, including Android 12+ extraction rules; test OEM behavior.
- Advertise an ephemeral endpoint and user-selected alias only; never phone/email/profile contents.
- Quick Nod defaults to a 60-second visibility window; stop on expiry, connection transition or cancellation. Shake, tile and manual launch share one domain command.
- Always Ready is optional sensor readiness, never permanent discovery. Respect foreground-service type/start restrictions, notification requirements and lock-screen/background activity limits. Do not assume `connectedDevice` legitimizes idle sensor listening.
- Request permissions contextually by API level. No broad storage/contact access when pickers/intents suffice. Detect GMS/radio capability failures and provide honest recovery.
- No guarantee of universal OEM behavior, automatic resume, handwriting accuracy or superior throughput without measurements.

## Engineering and evidence
- Pin dependency/plugin versions in the version catalog, keep Gradle wrapper checksum, review dependency provenance/licences and maintain `docs/engineering/DEPENDENCIES.md`. No signing keys, local SDK paths or secrets in source control.
- Use Java 17 compilation/toolchain; document any host runtime discrepancy. Keep release shrinker checks and database/protocol migration tests with the features they protect.
- Test consent gates, malformed protocol, replay/order, cancellation, integrity and cleanup. Run build, relevant unit tests and Android lint for code changes. Report unexecuted hardware/SDK checks honestly.
- Preserve source documents and original brand files. Do not publish policy drafts or invent legal ownership, support addresses, trademark status or package ownership.
- Update implementation status and evidence with each phase. A buildable scaffold is not the completed transfer proof of concept or a release-ready app.
- Maintain the root `TODO.md` checklist and `docs/engineering/STATUS.md` after meaningful milestones. Distinguish implementation from validation and provide an actual running-app preview at milestones when possible, as requested by the user.
