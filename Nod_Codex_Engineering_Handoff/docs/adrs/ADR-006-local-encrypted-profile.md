# ADR-006 — Local encrypted Nod Profile

Status: Accepted

## Decision

Nod Profile is maintained locally and sensitive fields are encrypted at rest using key material protected by Android Keystore.

## Consequences

No cloud identity is required. Sensitive data must be excluded from automatic backup unless a future explicit encrypted backup design is approved.
