# Phase 0 implementation mapping

Canonical plan: [supplied implementation plan](../../Nod_Codex_Engineering_Handoff/IMPLEMENTATION_PLAN.md). Tasks: [NOD-0001 onward](../../Nod_Codex_Engineering_Handoff/PHASE_0_1_BACKLOG.md). Follow its phases 0–7; this file maps only the build foundation.

## Exact Phase 0 skeleton

```
:app                 Android application and Hilt composition root
:core:model          JVM immutable models
:core:common         JVM dispatcher boundary
:core:protocol       JVM Protobuf lite generation and initial HELLO schema
:core:testing        JVM coroutine test support (test-only)
:domain              JVM diagnostic build-info interface; future use cases
:transport:api       JVM boundary reserved for NodTransport in Phase 1
:transport:nearby    Android adapter module; no radio implementation in Phase 0
:feature:spike       Android Compose diagnostic shell and ViewModel
```

Do not create empty profile/security/data/platform modules yet. Add them with working implementations in their phase. No placeholder transport pretending to work.

Dependency direction: app -> feature/spike + transport/nearby + domain; feature/spike -> domain -> core/model; transport/nearby -> transport/api. core/protocol is independent of transport SDKs. core/testing is test-only. Future session orchestration belongs in domain; serialization in core/protocol; ConnectionsClient stays inside transport/nearby.

## Implementation sequence

1. NOD-0009: preserve handoff by reference, reconcile root AGENTS, record API conflicts and source inventory.
2. NOD-0001/0002/0007: Git, checksum-pinned wrapper, Kotlin DSL/version catalog, Java 17, API 26/36, modules, debug/release, minimal Compose shell.
3. NOD-0005: Hilt + KSP composition root and injectable build-info interface; demonstrate test seam with fake dependency.
4. NOD-0006: Protobuf lite generation; HELLO round-trip and unknown-field preservation tests. Full schema/validation stays in Phase 1.
5. NOD-0003/0004: ktlint/detekt/lint, JUnit/coroutine/Turbine infrastructure and Compose test source. No radio/simulated-transfer behavior.
6. NOD-0008: local Gradle quality commands and GitHub Actions workflow ready for future remote; no upload implied.
7. Validate debug/release builds, JVM tests, lint/static checks; document clean-copy evidence and unexecuted device checks.

After Phase 0 exits, follow NOD-0100–0120 for the complete two-phone spike. The FILE private-staging feasibility issue belongs to Phase 1, before real-content transfer. No physical device was attached at inspection.

Temporary namespace: dev.nod. Application ID: dev.nod.spike, debug suffix .debug. No domain ownership is implied; finalize before public publication.
