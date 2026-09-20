# Nod UI reference register

Reviewed: 20 September 2026. This register reconciles the supplied visual assets with the updated planning conversation. It does not begin production UI implementation.

## Authority and visual reference

The primary visual reference is the exact repository-root image [ChatGPT Image Sep 19, 2026, 09_46_22 PM.png](../../ChatGPT%20Image%20Sep%2019,%202026,%2009_46_22%20PM.png), explicitly reaffirmed by the user. Open this image before production UI work. Preserve its white surfaces, charcoal typography, lemon accents, soft-green panels, rounded controls and spacing language while incorporating the accepted consent, privacy and Quick Nod states.

The exact logo source is [Brand/Nod_Locked_Logo_Reference.png](../../Brand/Nod_Locked_Logo_Reference.png). Product name: Nod. Tagline: Closer. Faster. Yours. Approved tokens remain lemon `#BFFF00`, white `#FFFFFF`, charcoal `#171717` and soft green `#F4FFE6`. Do not redraw the mark as text, change its geometry or colors, distort it, or replace it generatively. Preserve originals and provenance for any future clarity-only derivative; the small source is not a high-resolution master.

The user's latest product correction takes precedence over obsolete behavior shown in an otherwise authoritative visual reference. [ADR-011](../adrs/ADR-011-own-identity-only.md) records the accepted own-identity/business-card scope from [Android App Planning](chatgpt-conversation://6aacb020-7b2c-83ea-9529-2da8d3edf410), user turn `278816ea-9f94-4a60-99fe-f9d3da23ceaf`.

## Source and numbering limits

- The root board visibly contains screens 1–18. Its Select Contacts screen is retired by ADR-011; its styling remains a reference.
- The existing local `png/` and `svg/` files numbered 01–30 are an earlier flow set. They are not the same numbered series as the chat's later screen inventory below. Never match these sets by number alone.
- The updated chat contains a textual plan for screens 19–56. Retrieval exposed many subsequent turns as text-only "next" requests without the generated image contents. The subsequently supplied local `Nod_All_Screens/` now resolves that retrieval gap for named screens 19–27. An inventory entry alone is not evidence of a final asset or working screen.
- Six uploaded attachment references were returned separately by the conversation tool and preserved as four distinct files under `docs/design/reference/`. The implementation review inspected these available visuals. They do not include the missing generated screen-19-onward image-only turn assets or establish that every planned screen has a usable final asset.
- `Nod_All_Screens/README.txt` identifies original master screens 01–18 (11 is legacy), accepted additions 19–27, and exploratory/drifted concepts. It explicitly says screen 28 needs correction and Quick Nod imagery remains draft. The directory is flattened even though its README describes logical subfolders; use the named files and content, not a presumed directory layout.
- The new folder has 53 files: 51 PNGs, one JPEG contact sheet and one README, with no Android UI implementation source. Its immutable [file inventory](SCREENS_INVENTORY.json) records dimensions, sizes and hashes. The contact sheet and full-resolution named additions 19–27 were visually inspected; the master 01–18 flow was already reviewed on the primary board.
- Preserve all supplied assets. Do not replace missing generated images with invented reconstructions and label them approved. Use the [screen implementation table](SCREEN_IMPLEMENTATION_STATUS.md) to distinguish available designs from coded, connected and verified screens.

The [attachment provenance manifest](ATTACHMENTS.json) maps all returned names to preserved paths, SHA-256 hashes and the source conversation. Chat previews differ bytewise from existing root assets, so they are retained separately. The root board and locked logo remain authoritative.

| Available preserved attachment | Reviewed role |
|---|---|
| [Sep 20, 08:42 board](reference/ChatGPT%20Image%20Sep%2020,%202026,%2008_42_08%20AM%282%29.png) | Visually matches the original board; three returned attachment names share these bytes. |
| [Sep 20, 08:44 splash](reference/ChatGPT%20Image%20Sep%2020,%202026,%2008_44_05%20AM.png) | Clear single portrait splash reference. |
| [Sep 19, 10:31 splash](reference/ChatGPT%20Image%20Sep%2019,%202026,%2010_31_05%20PM.png) | Supporting splash preview retained separately from the root source. |
| [Standalone logo preview](reference/820ca382-acce-470c-bb54-167e8eaba9b3.png) | Supporting logo reference; does not replace the locked Brand source. |

## Changes to older flows

The accepted-file labels do not silently override locked product/security rules. Local screen 19 hardcodes a four-digit illustration; implementation must display the actual transport authentication token intact. Screen 22 currently depicts private values/photo before Receive Contact; preserve its visual structure but show only permitted offer metadata until acceptance. Screen 24 depicts duplicate lookup/update, which remains constrained by the no-broad-Contacts-read decision. Screen 25 includes QR/Nod ID elements and the set uses “Share. Instantly. Offline.”; these conflict with the retained no-QR scope and locked “Closer. Faster. Yours.” tagline. Record these conflicts rather than introducing accounts, QR flows or a brand change. Screen 27's Create New Profile is an available design proposal; custom preset behavior still needs its existing product-scope reconciliation before implementation.

The identity flow is: select a Share Profile from the user's encrypted local Nod Profile, review exactly the user's own fields, offer that selection, obtain receiver acceptance, transfer, then preview and optionally save the received card to Android Contacts. Before acceptance, an incoming offer shows permitted metadata and field labels only, not private field values or a profile photo.

Select Contacts, My Contacts, favorites, groups, All/Recent Contacts and contact search are outside the current product. Remove address-book selectors and related navigation from implementation plans. QR screens introduced during generation drift are also outside this accepted UI plan. Home must lead into own-card sharing and files without becoming a contact browser; suggested new button wording and layout details from the assistant are not automatically locked.

Older broad "Allow All" permission onboarding must give way to contextual permission flows. Older connected/success screens must not skip code comparison, receiver acceptance or integrity verification. A discovered alias or profile image is not proof of identity, and the discovery surface must not reveal private profile data. Transfer completion requires the agreed verified-finalization and acknowledgement sequence.

## Updated chat screen inventory

Numbers below identify the chat's textual inventory only. Apply the constraints in the last column before implementation; no row is a claim that a generated image was available for inspection.

| Chat screen | Planned state | Required interpretation |
|---|---|---|
| 19 | Peer verification | Display the same transport authentication code on both devices; both explicitly approve. |
| 20 | Select Share Profile | Quick, Personal or Professional selects fields from the user's own Nod Profile. |
| 21 | Review own fields | Show the exact outgoing selection to the sender before offering it. |
| 22 | Incoming card offer | Receiver accepts or rejects metadata/field labels before private card values transfer. |
| 23 | Received card preview/save | Show accepted, successfully received values; saving to Android Contacts is explicit. |
| 24 | Duplicate contact | Feasibility-gated; no broad Contacts read or silent merge is authorized. |
| 25 | Nod Profile | The user's encrypted local identity, distinct from Android Contacts. |
| 26 | Edit profile | Edit the user's own identity; keep sensitive values out of logs and backups. |
| 27 | Share Profiles | Presets select the user's own fields, not other people's contacts. |
| 28 | Edit preset | Preserve a clear review of included identity fields. |
| 29 | Quick Nod introduction | Explain temporary visibility and manual, tile and enabled shake entry points. |
| 30 | Active countdown | Default 60-second window; stop on expiry, connection transition or cancellation. |
| 31 | Device found | One remote peer per session; proceed through verification, never automatic acceptance. |
| 32 | Shake settings | Optional three-shake trigger with false-positive protection and cooldown. |
| 33 | Always Ready setup | Optional sensor readiness, subject to Android foreground/background constraints. |
| 34 | Always Ready active | Readiness is not permanent advertising or discovery. |
| 35 | Quick Settings tile setup | Tile invokes the same Quick Nod domain command as manual and shake entry. |
| 36 | Incoming file offer | Explicit acceptance for the exact manifest precedes content transfer. |
| 37 | Manifest details | Type, count and sizes are reviewable; validate all untrusted metadata. |
| 38 | Verifying files | Length and SHA-256 verification precede finalization and completed status. |
| 39 | Connection lost/reconnecting | Show the real state; reconnect/restart behavior must not imply proven resume. |
| 40 | Resume | Feasibility-gated; no promise of automatic or byte-offset resume. |
| 41 | Transfer failed | Typed failure and honest recovery; partial data never appears completed. |
| 42 | Integrity failed | Hard failure; prevent finalization and clean incomplete staging safely. |
| 43 | Low storage | Check capacity and fail safely without inventing success. |
| 44 | Peer unavailable | Explain loss/timeout with explicit retry or exit. |
| 45 | Contextual permission | Request only the permission required for the selected action and API level. |
| 46 | Permission denied | Honest explanation and supported recovery; no repeated coercive prompt loop. |
| 47 | Block confirmation | Distinguish session mute/throttling from any future authenticated durable block. |
| 48 | Blocked devices | Durable blocking is feasibility-gated; aliases/ephemeral endpoint IDs are insufficient. |
| 49 | Visibility | Preserve temporary Quick Nod and no permanent discovery by default. |
| 50 | Nearby alias | User-selected display text, separate from private identity and never trusted identity proof. |
| 51 | Settings | Reflect implemented capabilities and accepted product choices. |
| 52 | Privacy | Explain local profile, consent and actual SDK behavior accurately. |
| 53 | Cleanup | Distinguish partial staging from finalized user files; never silently delete successful output. |
| 54 | Transfer detail | Minimal history metadata, precise status and acknowledgement uncertainty when applicable. |
| 55 | About/legal | Preserve Nod branding; no invented owner, support address or published draft policies. |
| 56 | Locked-phone Quick Nod | Respect lock screen and background launch restrictions; use permitted user-visible entry. |

## Implementation gates

The Phase 0 launcher packages a byte-identical copy of the locked logo (`app/src/main/res/drawable-nodpi/nod_logo_reference.png`, SHA-256 `1c424b9b2822d9e987c5adc5ee801d9a2ce37a530a32c0e5042106d2870f56a0`). Android's adaptive-icon container centers it on white at its original 166:62 aspect ratio. No logo pixels were edited. The `MonochromeLauncherIcon` lint check is suppressed only on this resource because no recolored/themed variant is approved; production icon export remains a Phase 6 review item.

The planning conversation's UI "Phase 2" is a design-work label. It is not handoff engineering Phase 2, which covers the production protocol and transfer engine. Production UI remains handoff Phase 6, after the required technical spike and its physical-device gate. The plain Phase 0 shell does not replace this design system.

Resume, durable blocklists and duplicate-contact lookup require concrete feasibility and privacy decisions before their corresponding screens become functioning promises. See [API_REVIEW.md](../architecture/API_REVIEW.md). No screen proposal authorizes broad Contacts access, automatic content acceptance, permanent discovery or a relaxed file-integrity gate.

vCard export, custom cards and a dedicated Exchange Cards action were assistant suggestions in the correction response. They are not newly locked requirements. Existing agreed two-way sharing still requires independent consent in each direction; it does not establish a specific combined action or serialization format.

When production UI begins, inspect the exact primary board and relevant available supporting image for each flow, reconcile behavior against this register and current ADRs, and retain the original brand geometry. Implement accessibility, font scaling and Android system behavior within that visual language. Record any unresolved asset or product mismatch before treating a proposed replacement as final.
