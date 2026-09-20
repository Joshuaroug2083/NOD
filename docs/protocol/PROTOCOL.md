# Phase 0 protocol generation

The [supplied draft](../../Nod_Codex_Engineering_Handoff/docs/engineering/PROTOCOL_V1_DRAFT.md) remains canonical. `core/protocol/src/main/proto/nod_envelope.proto` implements only its envelope fields 1–3 and HELLO at field 10 to prove Protobuf lite generation and testing.

No network decoder, capability negotiation, validation or content-transfer behavior is implemented in Phase 0. Do not treat successful parsing as validation or trust. The remaining draft bodies (slots 11–18), limits, replay rules, session ownership and ACK semantics must be specified and tested during NOD-0107 onward before exchanging bytes with a peer.

Generated Java lite classes are consumed from Kotlin; no generated sources are committed. Tests demonstrate round-trip and unknown additive field preservation. This is an unreleased bootstrap schema, not a claimed complete v1 interoperability specification.
