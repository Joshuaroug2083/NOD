# ADR-004 — Versioned Protobuf control protocol

Status: Accepted

## Decision

Use Protocol Buffers for Nod control messages and version the protocol from the first implementation.

## Consequences

- Removed Protobuf field numbers must be reserved.
- Compatibility tests become release-critical.
- Control and data planes remain separate.
