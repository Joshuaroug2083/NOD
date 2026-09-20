# Nod — Implementation Plan

The implementation is deliberately staged. Do not begin with polished UI.

## Phase 0 — Repository and build foundation

Goal: reproducible Android project with quality gates.

Deliverables:

- Git repository initialized.
- Gradle Kotlin DSL.
- version catalog.
- Java 17 toolchain.
- minSdk 26 / targetSdk 36 / compileSdk 36 baseline.
- Compose enabled.
- Hilt + KSP wired.
- Coroutines/Flow.
- Protobuf generation.
- test infrastructure.
- lint + ktlint + detekt configuration.
- CI build workflow if CI provider is available.
- `debug`, `release`, and later benchmark/test configuration as needed.
- architecture package/module skeleton.
- `AGENTS.md` retained at repository root.

Exit criteria:

- clean clone builds;
- unit tests run;
- static analysis runs;
- no feature implementation yet beyond a minimal app shell.

## Phase 1 — Two-phone transport proof of concept

Goal: prove Nod's core promise on two physical phones.

Build a deliberately plain technical UI.

Required flow:

1. Device A advertises.
2. Device B discovers.
3. B requests connection.
4. Both display the same verification token.
5. Both explicitly approve.
6. A version/capability handshake completes.
7. A creates a manifest for a test file.
8. B displays count/name/type/size summary and accepts/rejects.
9. A sends the file.
10. Both show byte-level progress.
11. B verifies SHA-256.
12. B finalizes only after success.
13. B sends completion ACK.
14. Both disconnect cleanly.

Failure scenarios required in the spike:

- reject connection;
- authentication mismatch;
- reject manifest;
- cancel sender;
- cancel receiver;
- kill/background one app during transfer;
- disable Wi-Fi/Bluetooth as applicable;
- walk devices out of range;
- insufficient storage;
- intentional hash mismatch;
- duplicate destination filename;
- protocol-version mismatch.

Exit criteria:

- successful large-file transfer on at least two distinct physical Android device families;
- no corrupt file shown as complete after forced interruption;
- state machine returns to a sane terminal state after every tested failure.

## Phase 2 — Production protocol and transfer engine

- finalize protocol schema v1;
- strict envelope validation;
- capability negotiation;
- transfer item/payload correlation;
- staging/finalization abstractions;
- cleanup policy;
- cancellation semantics;
- typed errors;
- transfer history metadata;
- reliability metrics for internal testing.

## Phase 3 — Nod Profile and contact exchange

- encrypted local Nod Profile;
- share profiles/presets;
- nearby alias;
- import/select contact data through Android-approved flows;
- contact transfer protocol;
- receiver preview;
- duplicate detection;
- system contact insertion flow;
- privacy/backup exclusions.

## Phase 4 — Quick Nod and background readiness

- `StartQuickNodSession` use case;
- 60-second timed discovery/advertising;
- Quick Settings Tile;
- Shake ×3 detector;
- sensitivity/cooldown calibration;
- optional foreground service;
- notification UX;
- locked-device behavior;
- battery/OEM tests.

## Phase 5 — Number scanner

- CameraX capture/analyzer;
- bundled ML Kit text recognition;
- number candidate extraction;
- libphonenumber normalization;
- edit/review/copy flow;
- gallery input;
- handwriting accuracy benchmark.

## Phase 6 — Production UI/UX

Only after core reliability is proven:

- Nod design system;
- onboarding;
- home;
- discovery radar;
- verification;
- send/receive selectors;
- progress;
- completion;
- profile;
- settings;
- history;
- accessibility;
- reduced motion;
- dark mode only if product explicitly approves.

## Phase 7 — Hardening and release readiness

- device matrix testing;
- permission/version matrix;
- OEM background behavior;
- privacy review;
- security review;
- dependency/license review;
- Play Store Data Safety verification;
- crash/ANR analysis strategy;
- release signing;
- baseline profile/macrobenchmark;
- store listing assets;
- policy URLs;
- legal placeholders completed.
