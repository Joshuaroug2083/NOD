# Bootstrap dependency register

Exact versions live in `gradle/libs.versions.toml`; the wrapper pins Gradle 8.13 and its SHA-256. These are deliberately pinned compatible baseline versions, not a claim to be the newest releases. Upgrade through a reviewed build/test change.

Lint's `AndroidGradlePluginVersion`, `GradleDependency` and `NewerVersionAvailable` checks are informational and remain visible in reports. They identify available upgrades, not compatibility failures. Other lint warnings remain errors; there is no lint baseline hiding code defects. Review version changes as a set and validate them rather than automatically adopting every newest release during bootstrap. The launcher resource alone suppresses `MonochromeLauncherIcon` to preserve the locked colored logo until an approved themed variant exists; see the UI reference register.

| Component | Purpose | Upstream / licence family |
|---|---|---|
| Gradle, Android Gradle Plugin | Kotlin DSL build, Android SDK 36 | gradle.org / developer.android.com; Apache-2.0 |
| Kotlin and Compose compiler plugin | Kotlin/JVM 17 and Compose compilation | kotlinlang.org; Apache-2.0 |
| KSP, Dagger/Hilt | generated dependency injection | github.com/google/ksp, dagger.dev; Apache-2.0 |
| AndroidX Activity/Lifecycle/Compose/Material 3 | minimal shell, state lifecycle, instrumentation | developer.android.com/jetpack; Apache-2.0 |
| kotlinx.coroutines | structured concurrency, Flow/StateFlow | github.com/Kotlin/kotlinx.coroutines; Apache-2.0 |
| Protobuf compiler/Java lite | generated control envelope | protobuf.dev; BSD-3-Clause |
| Google Play services Nearby | selected adapter dependency, no calls in Phase 0 | developers.google.com/nearby; Google SDK terms, not assumed Apache-2.0 |
| JUnit 4 | JVM tests | junit.org/junit4; EPL-1.0 |
| Turbine | Flow testing | github.com/cashapp/turbine; Apache-2.0 |
| ktlint and Gradle integration | formatting checks | github.com/pinterest/ktlint, github.com/JLLeitschuh/ktlint-gradle; MIT |
| detekt | Kotlin static analysis | detekt.dev; Apache-2.0 |

Room, Proto DataStore, CameraX, bundled ML Kit, libphonenumber and WorkManager remain selected by the handoff but are intentionally not packaged until their implementation phase. No unused alternative SDKs are substituted.

This is the bootstrap direct-dependency register, not a completed release SBOM, legal clearance or third-party-notice bundle. Before distribution enumerate transitive components, preserve licence texts, audit SDK data behavior and generate notices. No public release is authorized by Phase 0.

The local JDK is Azul Zulu 17 (tool-only, ignored `.tools/` directory); CI uses Temurin 17. No machine-local SDK/JDK path is committed. Existing Android Studio bundled runtime was Java 21, so a separate Java 17 toolchain is used to retain the locked baseline.

## CI-only tools

The Android workflow pins every action to a resolved upstream commit. Major-version comments identify the reviewed release lines: GitHub checkout/setup-java/upload-artifact v4, android-actions/setup-android v3, Gradle setup-gradle v4 and ReactiveCircus/android-emulator-runner v2. These are build/test tools, not shipped app dependencies. Emulator execution follows the [upstream Linux/KVM setup](https://github.com/ReactiveCircus/android-emulator-runner); report/APK retention follows [GitHub artifact guidance](https://docs.github.com/en/actions/tutorials/store-and-share-data). Artifacts expire after 14 days. CI has read-only repository permissions and no release/signing credentials.

SDK setup explicitly requests `platform-tools` instead of the action's default `tools platform-tools`. The first hosted run, [35525315739](https://github.com/Joshuaroug2083/NOD/actions/runs/35525315739), failed in all three jobs because SDK Manager could not find the legacy `tools` package; no Gradle build or test ran. The pinned action still installs command-line tools independently, and subsequent steps install the selected platform/build tools and emulator images. This override uses the action's documented [packages input](https://github.com/android-actions/setup-android/blob/9fc6c4e9069bf8d3d10b2204b1fb8f6ef7065407/README.md#additional-packages), without ignoring SDK or test failures.

Hosted tests cover the diagnostic Compose shell on API 26 and 36. They do not provide physical Nearby or OEM evidence. The emulator action resolves SDK images at execution time; retain the run logs and installed image revisions when interpreting results. Changes limited to Markdown or `docs/` skip the Android workflow; manual dispatch remains available.

## Merged manifest inspection

The resolved Nearby SDK merges ACCESS_NETWORK_STATE, a permission-protected Exposure Notification WakeUpService and GoogleApiActivity into the debug manifest even before Nod calls Nearby. These are SDK components, not Nod product features. Inventory/audit them again when the adapter is implemented and before release. The Compose test-manifest dependency contributes a debug-only test host activity. Application backup remains false and explicit cloud/device-transfer exclusions remain attached. No dangerous nearby/contact/storage/camera permissions are requested by the Phase 0 shell.
