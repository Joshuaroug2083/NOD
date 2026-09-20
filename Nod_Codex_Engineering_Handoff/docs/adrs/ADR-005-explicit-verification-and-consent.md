# ADR-005 — Mutual verification and two-stage consent

Status: Accepted

## Decision

A user must verify the peer connection, and separately approve the content manifest before receiving content bytes.

## Consequences

Connection acceptance does not imply content acceptance. UI and session state must represent both stages.
