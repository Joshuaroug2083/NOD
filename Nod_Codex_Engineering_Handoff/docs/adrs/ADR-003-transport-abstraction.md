# ADR-003 — Transport abstraction with Nearby first

Status: Accepted

## Decision

Define `NodTransport`. Implement Google Nearby Connections `P2P_POINT_TO_POINT` first.

## Rationale

The product needs a fast two-device proof of concept, but the architecture must preserve the option for a future Wi-Fi Direct or non-GMS adapter.

## Consequences

No Nearby SDK types may leak above the transport adapter boundary.
