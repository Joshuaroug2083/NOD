# Phase 0 device validation

Recorded 20 September 2026. Scope: diagnostic shell rendering and the single Compose instrumentation test. No radio, file transfer or physical-device interoperability was tested.

## Available evidence

- The debug app rendered the expected Phase 0 screen on API 36; [screenshot](previews/phase0-shell.png).
- The first instrumentation attempt returned `Process crashed` while System UI and other emulator processes were unresponsive. No assertion result was returned.
- The library test APK originally defaulted to target API 26. The Gradle test target is now explicitly 36, and the packaged manifest was inspected after rebuilding. This improves target-behavior coverage; it is not claimed to fix the process-start failure.
- A second attempt used the already installed API 36 Google Play x86_64 image, emulator 36.1.9, a 540×960 display, Intel host graphics, Vulkan disabled, two virtual CPU cores and no concurrent Gradle daemon. It booted in approximately 80 seconds. The emulator enforced 2 GB of guest RAM despite attempts to request less in the first run.

## Bounded retry result

The test APK installed successfully. Direct `AndroidJUnitRunner` execution returned:

```text
INSTRUMENTATION_RESULT: shortMsg=Process crashed.
INSTRUMENTATION_CODE: 0
```

The command wrapper treated this as failure despite the Android shell command's misleading success-style exit. The retry allowed at most 60 seconds for instrumentation; Android terminated the process before that timeout.

Relevant ActivityManager evidence from the fresh log:

```text
17:48:06.611 Start proc 3283:dev.nod.feature.spike.test
17:48:40.666 ANR in dev.nod.feature.spike.test
Reason: Process ... dev.nod.feature.spike.test ... failed to complete startup
/proc/pressure/memory: some avg10=45.63 avg60=38.60 avg300=14.67
/proc/pressure/cpu:    some avg10=93.38 avg60=90.23 avg300=48.48
17:48:40.869 Killing 3283:dev.nod.feature.spike.test ... bg anr
```

`dumpsys activity exit-info dev.nod.feature.spike.test` independently reported:

```text
reason=6 (ANR)
description=bg anr: Process ... dev.nod.feature.spike.test ... failed to complete startup
```

The immediate failure is a process-start ANR. The emulator was under heavy CPU/memory pressure, and unrelated persistent Android processes were also failing/restarting. No completed JUnit/Compose assertion result was returned. This does not establish that the test or app is correct; a stable runtime rerun is still required. Do not suppress the test or claim that resource pressure alone rules out every app/test defect.

Detailed local logs remain in ignored `.inspection/instrumentation-retry.txt`, `.inspection/instrumentation-retry-logcat.txt` and `.inspection/test-process-exit-info.txt`. This document preserves the material evidence without committing broad emulator logs.

## Next validation

1. Use a physical Android device with USB debugging enabled, or a stable emulator with sufficient host memory headroom. No physical device is connected yet.
2. Run `gradlew.bat :feature:spike:connectedDebugAndroidTest`; retain its assertion report. The successful criterion is the actual test pass, not an APK build or shell exit code.
3. Run Android 8/API 26 minimum-support checks separately. An API 26 AOSP image download was attempted but timed out; no partial archive was installed.
4. Import/sync the project in an Android Studio version compatible with AGP 8.13.2. Installed Studio 2025.1.2 supports only through AGP 8.12; use 2025.1.3 or a newer compatible release.

Phase 0 remains open. Physical two-phone testing and production UI remain later phases in the canonical handoff.
