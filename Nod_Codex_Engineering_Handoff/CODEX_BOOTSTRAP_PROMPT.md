# First Codex Prompt for Nod

Paste the following into the first Codex coding chat after opening the Nod repository.

---

We are beginning implementation of Nod.

Read `AGENTS.md` first. Then read `PROJECT_CONTEXT.md`, `ARCHITECTURE.md`, `IMPLEMENTATION_PLAN.md`, `PHASE_0_1_BACKLOG.md`, `CODING_STANDARDS.md`, all ADRs under `docs/adrs/`, and the security/testing documents.

Treat the documented product decisions as authoritative. Do not silently simplify or redesign them.

Nod is a native Android-only offline peer-to-peer sharing application. Core locked decisions include:

- Android 8+ / minSdk 26.
- Kotlin.
- Jetpack Compose + Material 3.
- Coroutines / Flow / StateFlow.
- Hilt with KSP.
- Room.
- Proto DataStore.
- Protocol Buffers.
- Android Keystore + AES-GCM.
- Primary transport spike: Nearby Connections `P2P_POINT_TO_POINT`.
- Transport abstraction behind `NodTransport`.
- CameraX + bundled ML Kit Text Recognition.
- libphonenumber.
- SensorManager for Shake to Nod.
- optional foreground service for Always Ready.
- TileService for Quick Settings launch.
- MediaStore / Storage Access Framework / Android Contacts APIs.
- no backend for v1.
- no accounts.
- local encrypted Nod Profile.
- two-device sessions only.
- explicit mutual verification.
- explicit receiver content acceptance.
- temporary Quick Nod visibility window.
- Shake ×3 and Quick Settings Tile invoke the same Quick Nod use case.
- received files are staged, SHA-256 verified, then finalized.
- protocol is versioned from day one.

Do NOT start by building the polished UI.

First:

1. Inspect the repository and documents.
2. Report any contradictions, obsolete Android assumptions, or missing prerequisites.
3. Propose the exact Gradle/module skeleton for Phase 0, keeping modules minimal.
4. Produce a concise implementation sequence mapped to `NOD-0001` onward.
5. Then implement Phase 0 only.
6. Run build, unit tests, lint/static analysis.
7. Summarize files changed, commands run, failures, and recommended next task.

Do not proceed into Phase 1 until Phase 0 exits cleanly unless I explicitly authorize it.

---
