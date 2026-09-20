# ADR-001 — Native Android with Kotlin and Compose

Status: Accepted

## Decision

Build Nod v1 as a native Android application using Kotlin and Jetpack Compose.

## Rationale

Nod relies heavily on Android-specific wireless, sensor, storage, contacts, service, notification, and Quick Settings capabilities. Native APIs reduce abstraction leakage and improve control over lifecycle/background behavior.

## Consequences

- No Flutter/React Native abstraction in v1.
- iOS is a separate future product effort if pursued.
