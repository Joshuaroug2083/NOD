# Android/API review

Reviewed 20 September 2026. Product choices remain unchanged. This register separates actual conflicts from implementation constraints.

| Source assumption | Current constraint / resolution |
|---|---|
| Android 8+ for everyone | Nearby requires Google Play services. Keep minSdk 26 and show an unsupported transport state for non-GMS devices. Non-GMS v1 support commitment remains a release decision; Wi-Fi Direct stays behind NodTransport. |
| Wi-Fi on is sufficient | Nearby uses Wi-Fi/Bluetooth. A July 2026 Google announcement says automatic radio enabling ends in late 2026. Check readiness and ask users to enable radios through system UI. |
| All incoming files privately staged | Nearby FILE guide describes Downloads staging, with content-URI access on scoped-storage targets. The referenced public ConnectionsClient API does not expose a supported private-directory setter. This is a **Phase 1 feasibility gate**, not permission to expose partial files. Experiment with supported FILE behavior on API/OEM matrix; if no compliant route exists, record an ADR and explicit product decision before changing to STREAM/chunk framing or another transport. |
| Atomic rename into MediaStore/SAF | Private same-filesystem rename is not equivalent to publishing through a provider. API 29+ supports pending MediaStore entries; finish copy successfully before clearing IS_PENDING. API 26–28 and arbitrary SAF providers cannot be assumed to support pending/atomic publication. Keep spike finalization private; public export must have its own failure-safe design. |
| Always Ready uses connectedDevice FGS | That type covers external-device interactions, not automatically indefinite idle accelerometer listening. Review a suitable declared use case (possibly specialUse plus Play review) before background shake implementation. Optional mode remains locked; no silent always-scanning workaround. |
| Shake launches activity anywhere | Background launch and lock-screen restrictions apply even with FGS. Use user-visible notification/action and authenticated entry; never force an activity over the lock screen. |
| Tile launch via Intent | Android 14+ targets require the PendingIntent overload of startActivityAndCollapse; older APIs need the older path. |
| Permission sample can be pasted wholesale | Nearby sample includes unusual API bounds and legacy storage permissions. Runtime requests must use actual introduced API levels (NEARBY_WIFI_DEVICES is API 33), target behavior, and concrete payload URI needs. Do not request MANAGE_EXTERNAL_STORAGE or blindly add broad media access. Target 37+ WIFI_LAN adds ACCESS_LOCAL_NETWORK; target remains 36. |
| Transfer SUCCESS means complete | Nearby onPayloadReceived(FILE) can precede full receipt. Wait for payload SUCCESS, then verify actual length and SHA-256, then finalize, then exchange completion acknowledgement. Cross-type ordering is not guaranteed. |
| Blocklist uses stable peer ID | Nearby endpoint IDs are ephemeral and aliases are spoofable. V1 can throttle/mute session endpoints, but durable authenticated blocking needs a protocol identity design and a privacy review. Do not claim a permanent alias-based block. |
| Seamless resumable file transfer | Nearby FILE restart/resume cannot be promised from byte progress alone. Safely discard interrupted staging in the first spike; actual resumability is a separate protocol capability. |
| No internet/cloud dependency | Exchange has no Nod backend. GMS and bundled SDK diagnostics/distribution have separate behavior. Audit final SDKs; do not claim the entire device or all SDKs never communicate externally. |
| Logo/mockups are consistent | Older SVGs use #B6FF20/#111215 and text-based approximate mark. Later docs specify #BFFF00/#171717 and the locked image. Use the locked reference; retain older screens solely as flow references. The reference is only 166x62: do not enlarge and label it a high-resolution master. |
| Every accepted image can be implemented literally | `Nod_All_Screens` includes named 19–27 assets, but 22 shows private values before receiver consent, 24 assumes address-book duplicate lookup, 25 adds QR/Nod ID, and the set uses a different tagline. Preserve the supplied visual language while applying explicit consent/no-address-book/no-QR/locked-brand decisions. Show the actual transport token rather than hardcoding screen 19's illustrated four digits. Do not silently introduce these conflicting behaviors. |
| Older contact selectors and duplicate lookup | The updated planning conversation explicitly limits sharing to the user's own identity/card and files. No phone address-book browsing, broad Contacts read or redistribution is authorized. Use explicit system contact insertion for received cards; duplicate handling must stay within that permission boundary. See ADR-011 and the UI reference register. |
| API 36 target | Confirmed as current Play submission baseline from 31 August 2026. Keep compile/target 36. Do not repeat the conversation's unverified claim that API 37 is still a preview. |
| Installed Android Studio can import the pinned build | Local Studio is Narwhal Feature Drop 2025.1.2, whose supported AGP range ends at 8.12. Nod pins AGP 8.13.2. Use a compatible Studio release (Narwhal 3 Feature Drop 2025.1.3 or newer compatible release) for IDE validation; this does not require changing the locked SDK/Kotlin product baseline. |

## Primary references

- [Nearby setup and permissions](https://developers.google.com/nearby/connections/android/get-started)
- [Nearby file delivery, scoped-storage URI and ordering](https://developers.google.com/nearby/connections/android/exchange-data)
- [ConnectionsClient public API](https://developers.google.com/android/reference/com/google/android/gms/nearby/connection/ConnectionsClient)
- [Nearby radio changes announced July 2026](https://android-developers.googleblog.com/2026/07/upcoming-changes-nearby-connections-api.html)
- [Foreground service types](https://developer.android.com/develop/background-work/services/fgs/service-types)
- [TileService API](https://developer.android.com/reference/android/service/quicksettings/TileService)
- [MediaStore pending writes](https://developer.android.com/training/data-storage/shared/media)
- [Play target API requirements](https://support.google.com/googleplay/android-developer/answer/11926878)
- [AGP 8.13 compatibility](https://developer.android.com/build/releases/agp-8-13-0-release-notes)
- [Android Studio / AGP compatibility](https://developer.android.com/studio/releases#android_gradle_plugin_and_android_studio_compatibility)
