# Implementation evidence

Updated 20 September 2026. Phase 0 foundation and clean-clone validation are implemented; successful device-test execution and compatible IDE import remain outstanding. Phase 1 has not started. Track individual deliverables and milestone previews in [TODO.md](../../TODO.md).

The application is a diagnostic shell only. There is no discovery, pairing, transfer, profile storage, Quick Nod, OCR or production UI implementation yet.

## Executed checks

| Check | Evidence / result |
|---|---|
| Source preservation | Independent audit verified all 127 original inventory entries by size and SHA-256; none changed. |
| Build toolchain | Gradle 8.13 wrapper; Azul Zulu Java 17; compile/target 36, minimum 26. Local machine initially supplied Java 21, so ignored local JDK 17 is used. |
| Protocol JVM tests | 2 passed, 0 failed/skipped: generated HELLO round-trip and preservation of unknown additive fields. |
| ViewModel JVM test | 1 passed, 0 failed/skipped: StateFlow diagnostic state through injected fake dependency. |
| `qualityCheck` | Completed successfully in the final validation run: ktlint, detekt, relevant JVM tests and Android debug lint. |
| App lint | 0 errors, 0 warnings, 20 informational version-update notices. No code-defect baseline. |
| Debug APK | `app/build/outputs/apk/debug/app-debug.apk` produced successfully. |
| Compose test APK / execution | APK produced. Both attempts returned `Process crashed`. Fresh retry logs and Android process exit history identify a process-start ANR under heavy emulator pressure; no completed test assertion result. See [device validation evidence](DEVICE_VALIDATION.md). Test remains failed/unverified pending a stable-runtime rerun. |
| Release build | Passed, including R8 and resource shrinking; unsigned `app/build/outputs/apk/release/app-release-unsigned.apk` produced. Combined validation finished successfully in 6m 22s (387 actionable tasks: 117 executed, 270 up-to-date). |
| Source-copy build | Passed in 4m 52s (107 actionable tasks: 98 executed, 9 up-to-date). 246 non-ignored files copied without local settings/build outputs; `clean :app:assembleDebug --offline --no-build-cache --max-workers=2` used Java 17, a 1536 MB Gradle heap and warm shared dependency cache. Kotlin reported a Windows file lock on generated dirty-sources state, then successfully compiled via its fallback strategy. First concurrent attempt was stopped to reduce host memory pressure. |
| Literal clean-clone build | Local checkpoint `b077090` on `codex/phase0-foundation` cloned with `git clone --no-hardlinks --no-checkout` and checked out separately. `:app:assembleDebug --offline --no-build-cache` passed in 2m 55s; all 98 actionable tasks executed. No project outputs or local.properties copied; SDK/JDK supplied through environment and shared dependency cache was warm. Both inventories matched in the clone: 127 original sources and 53 new screen-package files, zero hash differences. |
| Runtime preview | Installed and launched `dev.nod.spike.debug/dev.nod.app.MainActivity` on task-local API 36 / Pixel 6 profile / x86_64 emulator 36.1.9. The activity rendered the expected shell; [actual screenshot](previews/phase0-shell.png) captured. Initial launch wait timed out before the activity rendered. The emulator also suffered System UI, input-method and phone-process ANRs during severe host memory pressure; this is not performance or stability acceptance. |

Final validation command: `gradlew.bat :app:clean qualityCheck :app:assembleDebug :app:assembleRelease :feature:spike:assembleDebugAndroidTest --no-build-cache --console=plain` with Java 17. Console log is in ignored `.inspection/validation-final4.log`; durable outcomes belong in this document, not only that local log.

Initial lint found a diagnostic pluralization candidate and missing launcher resource; both were corrected. Dependency update detectors remain informational because versions are intentionally pinned as a compatible set. The launcher alone suppresses `MonochromeLauncherIcon` because the locked logo has no approved recolored variant. A stale resource result after moving the icon folder required a clean build with task-output cache disabled; that build passed resource linking.

## Remaining exit evidence

### Follow-up validation

The library instrumentation manifest was found to default to target API 26. `testOptions.targetSdk` now explicitly sets 36 to match the app. This is a test-coverage correction, not a confirmed explanation of the earlier process crash. Gradle now uses a 1536 MB heap, two workers and in-process Kotlin compilation to limit host memory use. The aggregate quality/debug/release/instrumentation build passed again in 1m 23s (386 tasks: 8 executed, 378 up-to-date); inspection confirmed the regenerated test manifest targets 36.

The installed Android Studio version (Narwhal Feature Drop 2025.1.2) supports AGP through 8.12, while this project uses 8.13.2. IDE import requires a compatible newer Studio version; the build dependency is retained and the requirement is documented in BUILD.md/API_REVIEW.md. A lighter API 26 AOSP image was attempted, but SDK Manager stalled and a direct Google archive download timed out after 180 seconds with only 1.27 MB of 474.18 MB received. No incomplete image was installed or run. A bounded retry of the existing API 36 emulator used a smaller display, host GPU and no concurrent Gradle daemon, but instrumentation failed again with the process-start ANR documented below.

- Rerun Compose instrumentation on a stable emulator/device. The second attempt's immediate cause is confirmed as an Android process-start ANR; both host and guest showed severe resource pressure, with unrelated system processes also failing. No JUnit pass is claimed, and app/test correctness remains unverified. See DEVICE_VALIDATION.md.
- Android Studio import/sync has not been observed. The installed IDE is incompatible with AGP 8.13; see the follow-up evidence above. A command-line build is not IDE evidence.
- A local Git checkpoint and literal clone now exist; nothing has been pushed and no external remote was configured in the working repository.
- GitHub Actions workflow is configured but has not run remotely.
- No physical phones have been tested. All Phase 1 radio, consent, integrity, throughput and OEM acceptance checks remain unexecuted.

The updated UI register and ADR-011 preserve the user's own-card-only correction. The newly supplied `Nod_All_Screens` folder resolves the missing references for named screens 19–27 and supplies 01–18 assets. Its README marks screen 11 legacy, screen 28 in need of correction and Quick Nod images draft. No product screen has been implemented yet; see the [screen status table](../design/SCREEN_IMPLEMENTATION_STATUS.md).

## Preview and artifacts

- [Foundation shell screenshot](previews/phase0-shell.png): real app rendering, not a production UI mockup.
- [Initial emulator System UI ANR screenshot](previews/phase0-emulator-system-ui-anr.png): retained as failure evidence rather than hidden from the record.
- [APK sizes and SHA-256 hashes](PHASE0_ARTIFACTS.json): debug, unsigned release and instrumentation APKs from the successful combined build.
- [Detailed implementation tracker](../../TODO.md) and [screen status table](../design/SCREEN_IMPLEMENTATION_STATUS.md): distinguish design assets, built UI, connected behavior and verified flows.

Preview tooling used the installed API 36 Google Play x86_64 system image and task-local AVD under ignored `.tools/avd`. Official Android command-line tools archive `commandlinetools-win-15859902_latest.zip` was verified against SHA-256 `90ae805d20434428bffcb699c290860f19bb5f66a67e6b330067e3de801fb04a` from the [Android download page](https://developer.android.com/studio), then installed in SDK `cmdline-tools/nod-bootstrap`. No accounts were added. The task-created headless emulator was stopped after evidence capture to release memory.
