# Nod — Codex Engineering Handoff

This repository handoff defines the engineering constraints, architecture, implementation order, and first technical spike for **Nod**, a native Android app for private, offline, two-device sharing.

## Start here

Read in this order before writing production code:

1. `AGENTS.md`
2. `PROJECT_CONTEXT.md`
3. `ARCHITECTURE.md`
4. `IMPLEMENTATION_PLAN.md`
5. `PHASE_0_1_BACKLOG.md`
6. `CODING_STANDARDS.md`
7. `docs/adrs/`
8. `docs/security/SECURITY_RULES.md`
9. `docs/testing/TEST_STRATEGY.md`
10. `CODEX_BOOTSTRAP_PROMPT.md`

## Product summary

**Nod** lets two nearby Android phones discover one another, mutually verify the connection, and exchange contacts, photos, videos, and documents without requiring public internet access or a Nod cloud account.

The product is intentionally local-first:

- no Nod account in v1;
- no Nod backend in v1;
- no cloud profile dependency;
- Nod Profile remains on-device;
- transfers require explicit user consent;
- only two devices participate in a session in v1;
- discovery is temporary, not permanently visible by default.

## Locked brand/product decisions

- Product name: **Nod**.
- The existing Nod logo is final and must not be redesigned.
- Primary visual language: white, black/charcoal, lemon green.
- Android only for v1.
- Minimum Android support: API 26 / Android 8.0.
- Native Kotlin + Jetpack Compose.
- Primary transport prototype: Google Nearby Connections, `P2P_POINT_TO_POINT`, behind `NodTransport`.
- Versioned Protobuf protocol from the first spike.
- Receiver approval before content bytes are transferred.
- Mutual verification before a session becomes trusted.
- Quick Nod: temporary discovery window, default target 60 seconds.
- Shake ×3 and Quick Settings Tile both invoke the same Quick Nod domain action.
- Always Ready is optional and must not mean continuous discovery.

## Definition of "done" for the first spike

Two physical Android phones can:

1. advertise/discover;
2. request connection;
3. display the same verification code;
4. explicitly approve;
5. establish a session;
6. negotiate protocol version/capabilities;
7. exchange a transfer manifest;
8. let the receiver accept/reject;
9. transfer a large file with byte-level progress;
10. verify SHA-256;
11. atomically finalize only after integrity success;
12. acknowledge completion;
13. cancel/disconnect cleanly;
14. leave no false-success or corrupt final file after interruption.

The polished production UI is deliberately **not** part of the first spike.
