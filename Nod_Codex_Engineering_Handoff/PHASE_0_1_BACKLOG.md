# Nod — Phase 0 & Phase 1 Engineering Backlog

Priority: P0 = blocking, P1 = important, P2 = follow-up.

## Phase 0 — Foundation

### NOD-0001 — Create Android project [P0]

Acceptance criteria:

- Kotlin project opens in Android Studio.
- minSdk 26, target/compile 36 baseline.
- Java 17 toolchain.
- package/application ID uses a temporary documented namespace until the final legal domain/package is confirmed.

### NOD-0002 — Configure Gradle conventions [P0]

- Kotlin DSL.
- version catalog.
- central Compose/Kotlin compiler configuration.
- reproducible debug build.

### NOD-0003 — Configure code quality [P0]

- Android Lint.
- ktlint.
- detekt.
- all run from Gradle.

### NOD-0004 — Configure tests [P0]

- JUnit.
- coroutine test utilities.
- Turbine or equivalent Flow testing utility if adopted.
- Compose UI test setup.

### NOD-0005 — Configure Hilt/KSP [P0]

- app builds with injection.
- test seam demonstrated.

### NOD-0006 — Configure Protobuf [P0]

- `.proto` source directory.
- generated Kotlin/Java lite artifacts as appropriate.
- encode/decode unit test.

### NOD-0007 — Create initial module boundaries [P0]

- app.
- core model/common/protocol.
- domain.
- transport api/nearby.
- feature spike.
- testing.

Avoid unnecessary extra modules.

### NOD-0008 — Add CI build workflow [P1]

- assemble debug.
- unit tests.
- lint/static analysis.

### NOD-0009 — Add repository docs [P0]

- preserve this handoff.
- add architecture decision index.
- add local development instructions.

## Phase 1 — Transport spike

### NOD-0100 — Define transport models [P0]

Create strongly typed:

- PeerId.
- EndpointAlias.
- ConnectionRequestId.
- TransportPayloadId.
- transport events.

### NOD-0101 — Implement `NodTransport` API [P0]

No Nearby types may escape the transport adapter.

### NOD-0102 — Implement Nearby adapter bootstrap [P0]

- obtain `ConnectionsClient` internally.
- P2P point-to-point strategy.
- map discovery/connection callbacks to Flow events.

### NOD-0103 — Technical advertising screen [P0]

Plain UI:

- Start advertising.
- Stop advertising.
- display local endpoint alias/status.

### NOD-0104 — Technical discovery screen [P0]

- Start discovery.
- Stop discovery.
- list discovered peers.
- connect action.

### NOD-0105 — Verification flow [P0]

- both devices expose same auth token/code.
- human must accept on both.
- mismatched/rejected verification tears down connection.

### NOD-0106 — Session state machine [P0]

Implement tested states for:

- idle;
- discovering/advertising;
- request pending;
- verifying;
- connected;
- manifest pending;
- transferring;
- verifying payload;
- complete;
- failed/cancelled/disconnected.

### NOD-0107 — Protocol v1 envelope [P0]

- protocol version.
- session ID.
- message ID.
- body union.
- hello/capabilities.

### NOD-0108 — Protocol negotiation [P0]

- both endpoints exchange supported version/capabilities.
- unsupported version fails clearly.

### NOD-0109 — Transfer manifest [P0]

Include at minimum:

- transfer ID;
- item ID;
- display filename;
- MIME type;
- byte size;
- SHA-256;
- total bytes.

### NOD-0110 — Receiver accept/reject [P0]

No file payload starts before acceptance.

### NOD-0111 — File staging abstraction [P0]

- private temporary destination.
- sufficient-space check.
- deterministic cleanup.

### NOD-0112 — Large-file send/receive [P0]

- use transport file payload.
- real byte progress.
- no entire-file memory buffering.

### NOD-0113 — Payload/item correlation [P0]

Explicit mapping between protocol item and Nearby payload ID. Do not rely on arrival order.

### NOD-0114 — SHA-256 verification [P0]

- receiver computes hash after/incrementally during receipt as designed.
- mismatch prevents finalization.

### NOD-0115 — Atomic finalization [P0]

- completed file appears only after integrity validation.
- duplicate destination filename handled safely.

### NOD-0116 — Completion acknowledgement [P0]

- receiver sends success/failure.
- sender only shows complete after defined acknowledgement policy.

### NOD-0117 — Cancellation [P0]

- sender cancel.
- receiver cancel.
- temporary cleanup.
- peer receives cancellation reason/category.

### NOD-0118 — Disconnect/range-loss behavior [P0]

- state returns to terminal failure or resumable state per spike decision.
- no false success.

### NOD-0119 — Physical-device matrix [P0]

At minimum two different OEM/device families before Phase 1 is declared complete.

### NOD-0120 — Spike report and ADR updates [P0]

Document:

- observed throughput;
- setup latency;
- permission friction;
- failure modes;
- OEM differences;
- whether Nearby remains primary transport;
- whether resume is feasible for v1.
