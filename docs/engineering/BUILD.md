# Local build and validation

## Prerequisites

- JDK 17, set `JAVA_HOME` to its installation directory.
- Android SDK platform 36 and Build Tools 35.0.0. Set `ANDROID_HOME` or create ignored `local.properties` with `sdk.dir`.
- Internet is required to fetch build dependencies on first build; this does not introduce a cloud requirement into Nod's exchange runtime.
- Use the checked-in Gradle 8.13 wrapper. No global Gradle installation is needed.
- For IDE import, use Android Studio Narwhal 3 Feature Drop (2025.1.3) or a newer version compatible with AGP 8.13. The installed Narwhal Feature Drop 2025.1.2 supports through AGP 8.12 and cannot validate this pinned project's IDE sync. See the [official compatibility table](https://developer.android.com/studio/releases#android_gradle_plugin_and_android_studio_compatibility). Command-line builds do not require Android Studio.

## Commands

Windows PowerShell:

```powershell
.\gradlew.bat qualityCheck :app:assembleDebug :app:assembleRelease :feature:spike:assembleDebugAndroidTest
```

Linux/macOS:

```sh
bash ./gradlew qualityCheck :app:assembleDebug :app:assembleRelease :feature:spike:assembleDebugAndroidTest
```

`qualityCheck` includes ktlint, detekt, JVM/unit tests and Android debug lint. `assembleRelease` runs the configured R8/resource shrinker and produces an unsigned artifact; it does not publish or sign for production. Run `ktlintFormat` to apply Kotlin formatting.

The default Gradle heap is 1536 MB with at most two workers, and Kotlin compiles in the Gradle process to avoid a second compiler JVM on smaller hosts. Run builds and emulators sequentially on an 8 GB machine. A larger host can override these Gradle properties locally without changing the project baseline. Library instrumentation APKs explicitly target API 36, matching the application rather than defaulting to minimum API 26.

With an emulator/device attached:

```powershell
.\gradlew.bat :feature:spike:connectedDebugAndroidTest
```

The instrumented shell test verifies rendering only. It is not a wireless transfer test. Nearby requires two real devices for the Phase 1 acceptance gate.

The `.github/workflows/android.yml` workflow can run when this repository is hosted on GitHub. No remote repository has been created or pushed by bootstrap.

## Outputs

- Debug APK: `app/build/outputs/apk/debug/app-debug.apk`
- Unsigned release APK: `app/build/outputs/apk/release/app-release-unsigned.apk`
- Unit-test reports: each module's `build/reports/tests/`
- Lint/static-analysis reports: each module's `build/reports/`

See `STATUS.md` for actual execution evidence and remaining gates; setup instructions are not evidence of a pass.

The current foundation preview is [the actual emulator screenshot](previews/phase0-shell.png). Install the debug APK on a development device with `adb install -r app/build/outputs/apk/debug/app-debug.apk`, then launch `dev.nod.spike.debug/dev.nod.app.MainActivity`. This shell has no discovery or transfer behavior. The first local instrumentation attempt failed; read `STATUS.md` before treating setup as validated.
