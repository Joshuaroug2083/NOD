# ADR-008 — Stage, verify, then finalize incoming files

Status: Accepted

## Decision

Incoming files are written to private temporary storage, validated for size/integrity, then finalized to the user-visible destination.

## Consequences

Interrupted/corrupt content must not be presented as a successful final file. Cleanup is mandatory.
