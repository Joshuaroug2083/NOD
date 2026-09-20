# ADR-002 — Local-first, no required backend in v1

Status: Accepted

## Decision

Core Nod sharing, identity, and history work without a Nod cloud backend or account.

## Consequences

- Uninstall may remove local profile/history unless a future explicit export/import feature is added.
- Core privacy claims depend on excluding sensitive data from unintended cloud backup.
- Analytics/support telemetry must not become a hidden backend dependency for core use.
