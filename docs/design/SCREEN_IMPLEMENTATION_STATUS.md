# Screen implementation status

Updated: 20 September 2026. **No production product screen is implemented, connected to its feature, or verified end to end yet.** The only Android UI in code is the plain Phase 0 diagnostic shell in `feature:spike`. Design PNGs are reference assets, not executable app screens.

The [main TODO](../../TODO.md) tracks engineering work. This table tracks screen delivery separately so a design image is never mistaken for working functionality.

## Status definitions

- **Design available:** the source image exists; this does not mean all depicted behavior is authorized or technically proven.
- **UI built:** a real Compose screen renders and navigates correctly against the reference.
- **Feature connected:** the screen uses implemented ViewModel/domain logic and real platform/transport data.
- **Verified:** required functional, negative-path and accessibility checks pass; radio-dependent flows need physical-device evidence.
- A working mock UI alone cannot satisfy Feature connected or Verified. Store screenshot/test links when changing a status.

## Available named designs 01–27

Source: [Nod_All_Screens README](../../Nod_All_Screens/README.txt). The folder contains 51 PNGs, one contact sheet JPEG and this README (53 files total). It supplies the 18-screen master flow and accepted additions 19–27, with screen 11 explicitly retired. The other images are references or exploration; they do not establish 51 unique accepted product screens.

Original brand and product/security decisions remain authoritative. See [UI reference register](UI_REFERENCE.md), [API review](../architecture/API_REVIEW.md) and [ADR-011](../adrs/ADR-011-own-identity-only.md).

| # | Design reference | UI built | Feature connected | Verified | Feature / UI phases | Acceptance constraint |
|---|---|---|---|---|---|---|
| 01 | [Splash / Get Started](../../Nod_All_Screens/01_Splash_Get_Started.png) | Not started | Not started | Not started | 6 | Native launch and onboarding entry. |
| 02 | [Welcome](../../Nod_All_Screens/02_Onboarding_Welcome_to_Nod.png) | Not started | Not started | Not started | 6 | Approved visual reference; no contact-browser promise. |
| 03 | [How it works](../../Nod_All_Screens/03_Onboarding_How_It_Works.png) | Not started | Not started | Not started | 6 | Explain own-card/files, verification and consent. |
| 04 | [Own profile setup](../../Nod_All_Screens/04_Profile_Setup.png) | Not started | Not started | Not started | 3, 6 | Encrypted local identity only. |
| 05 | [Permissions](../../Nod_All_Screens/05_Permissions.png) | Not started | Not started | Not started | 1 onward, 6 | Request contextually; replace broad Allow All behavior. |
| 06 | [Setup complete](../../Nod_All_Screens/06_Setup_Complete.png) | Not started | Not started | Not started | 3, 6 | Show only when required setup is valid. |
| 07 | [Home](../../Nod_All_Screens/07_Home.png) | Not started | Not started | Not started | 6 | Own-card/files actions; no address-book navigation. |
| 08 | [Nearby discovery](../../Nod_All_Screens/08_Discovering_Devices.png) | Not started | Not started | Not started | 1, 6 | Real transport events and ephemeral alias only. |
| 09 | [Connection request](../../Nod_All_Screens/09_Connection_Request_Receiver.png) | Not started | Not started | Not started | 1, 6 | Explicit connection approval, followed by code verification. |
| 10 | [Connected](../../Nod_All_Screens/10_Connected.png) | Not started | Not started | Not started | 1, 6 | Both approvals plus negotiated session; content consent still separate. |
| 11 | [Select contacts — RETIRED](../../Nod_All_Screens/11_Select_Contacts_Sender_LEGACY.png) | Retired | Retired | Retired | None | Outside current scope; retain image as historical reference. |
| 12 | [Select files](../../Nod_All_Screens/12_Select_Files_Sender.png) | Not started | Not started | Not started | 2, 6 | System picker/platform URI access and validated manifest. |
| 13 | [Sending progress](../../Nod_All_Screens/13_Sending_Progress_Sender.png) | Not started | Not started | Not started | 1, 2, 6 | Actual 64-bit byte progress; no simulated transfer. |
| 14 | [Receiving progress](../../Nod_All_Screens/14_Receiving_Progress_Receiver.png) | Not started | Not started | Not started | 1, 2, 6 | Private incomplete staging; verification follows receipt. |
| 15 | [Sender completion](../../Nod_All_Screens/15_Transfer_Complete_Sender.png) | Not started | Not started | Not started | 1, 2, 6 | Requires defined peer ACK; account for uncertainty. |
| 16 | [Receiver completion](../../Nod_All_Screens/16_Received_Successfully_Receiver.png) | Not started | Not started | Not started | 1, 2, 6 | Only after length/hash verification and finalization. |
| 17 | [Number scanner](../../Nod_All_Screens/17_Scan_Phone_Number.png) | Not started | Not started | Not started | 5, 6 | Camera/gallery OCR with review/edit/copy. |
| 18 | [Transfer history](../../Nod_All_Screens/18_Transfer_History.png) | Not started | Not started | Not started | 2, 6 | Minimal metadata and accurate terminal status. |
| 19 | [Peer verification](../../Nod_All_Screens/19_Secure_Connection_Verification.png) | Not started | Not started | Not started | 1, 6 | Actual token intact; explicit comparison/approval on both phones. |
| 20 | [Select Share Profile](../../Nod_All_Screens/20_Select_Share_Profile.png) | Not started | Not started | Not started | 3, 6 | Own Quick/Personal/Professional fields only. |
| 21 | [Review own card](../../Nod_All_Screens/21_Review_Contact_Before_Sending.png) | Not started | Not started | Not started | 3, 6 | Review exact outgoing field selection. |
| 22 | [Incoming card offer](../../Nod_All_Screens/22_Incoming_Contact_Offer.png) | Not started | Not started | Not started | 3, 6 | Image values/photo must become permitted metadata before acceptance. |
| 23 | [Received card preview](../../Nod_All_Screens/23_Received_Contact_Preview.png) | Not started | Not started | Not started | 3, 6 | Explicit Android system contact insertion. |
| 24 | [Duplicate contact](../../Nod_All_Screens/24_Duplicate_Contact_Detected.png) | Not started | Not started | Not started | 3, 6 | Feasibility-gated; no broad Contacts read or silent merge. |
| 25 | [Nod Profile](../../Nod_All_Screens/25_Nod_Profile.png) | Not started | Not started | Not started | 3, 6 | QR/Nod ID artwork is not authority to add QR/accounts. |
| 26 | [Edit Nod Profile](../../Nod_All_Screens/26_Edit_Nod_Profile.png) | Not started | Not started | Not started | 3, 6 | Encrypted profile editing and separate nearby alias. |
| 27 | [Share Profiles](../../Nod_All_Screens/27_Share_Profiles.png) | Not started | Not started | Not started | 3, 6 | Preset selection/editing; custom-creation scope requires reconciliation. |

## Planned or draft designs 28–56

The package README explicitly says screen 28 needs regeneration/correction and Quick Nod images are drafts. No complete approved named set 28–56 exists in this folder. The following rows retain the planning conversation's requirements; final source assets must be reconciled when the relevant UI work starts.

| # | Planned screen | Design state | UI built | Feature connected | Verified | Feature / UI phases | Acceptance constraint |
|---|---|---|---|---|---|---|---|
| 28 | Edit Share Profile | Needs correction | Not started | Not started | Not started | 3, 6 | Package README says image needs correction. |
| 29 | Quick Nod introduction | Draft / planned | Not started | Not started | Not started | 4, 6 | Existing Quick Nod exploration is draft. |
| 30 | Active visibility countdown | Draft / planned | Not started | Not started | Not started | 4, 6 | 60 seconds by default; no permanent discovery. |
| 31 | Quick Nod device found | Draft / planned | Not started | Not started | Not started | 4, 6 | Exactly one peer and mandatory verification. |
| 32 | Shake settings | Draft / planned | Not started | Not started | Not started | 4, 6 | Optional three-shake trigger, sensitivity and cooldown. |
| 33 | Always Ready setup | Draft / planned | Not started | Not started | Not started | 4, 6 | Foreground-service feasibility and explicit opt-in. |
| 34 | Always Ready active | Draft / planned | Not started | Not started | Not started | 4, 6 | Sensor readiness only; not continuous discovery. |
| 35 | Quick Settings tile setup | Draft / planned | Not started | Not started | Not started | 4, 6 | Same domain trigger as manual/shake. |
| 36 | Incoming file offer | Planned | Not started | Not started | Not started | 1, 2, 6 | Explicit acceptance of exact manifest. |
| 37 | Manifest details | Planned | Not started | Not started | Not started | 1, 2, 6 | Validated item count, names, types and sizes. |
| 38 | Verifying files | Planned | Not started | Not started | Not started | 1, 2, 6 | Length/SHA-256 before finalization. |
| 39 | Connection lost / reconnect | Planned | Not started | Not started | Not started | 1, 2, 6 | Honest terminal/retry state; no invented resume. |
| 40 | Resume transfer | Planned | Not started | Not started | Not started | 2, 6 | Capability and feasibility decision required. |
| 41 | Transfer failed | Planned | Not started | Not started | Not started | 1, 2, 6 | Typed error and safe recovery. |
| 42 | Integrity failed | Planned | Not started | Not started | Not started | 1, 2, 6 | Prevent publication and clean partial data. |
| 43 | Insufficient storage | Planned | Not started | Not started | Not started | 1, 2, 6 | Fail safely before/during writes. |
| 44 | Peer unavailable | Planned | Not started | Not started | Not started | 1, 2, 6 | Timeout/loss with retry or exit. |
| 45 | Contextual permission | Planned | Not started | Not started | Not started | 1 onward, 6 | Only the selected action's API-specific permission. |
| 46 | Permission denied | Planned | Not started | Not started | Not started | 1 onward, 6 | Supported recovery without repeated coercive prompts. |
| 47 | Block confirmation | Planned | Not started | Not started | Not started | 2, 6 | Session mute/throttling versus authenticated durable block. |
| 48 | Blocked devices | Planned | Not started | Not started | Not started | 2, 6 | Durable identity design needed; aliases are insufficient. |
| 49 | Visibility settings | Planned | Not started | Not started | Not started | 4, 6 | Timed Quick Nod; no permanent discovery by default. |
| 50 | Nearby alias | Planned | Not started | Not started | Not started | 3, 6 | Untrusted display name separate from private profile. |
| 51 | Settings | Planned | Not started | Not started | Not started | 3–6 | Reflect implemented and accepted capabilities. |
| 52 | Privacy | Planned | Not started | Not started | Not started | 3, 6 | Accurate local data/SDK/consent description. |
| 53 | Cleanup | Planned | Not started | Not started | Not started | 2, 6 | Separate partial staging from finalized user files. |
| 54 | Transfer detail | Planned | Not started | Not started | Not started | 2, 6 | Accurate history and ACK uncertainty. |
| 55 | About / legal | Planned | Not started | Not started | Not started | 6, 7 | Verified owner/support details; no published drafts. |
| 56 | Locked-phone Quick Nod | Planned | Not started | Not started | Not started | 4, 6 | Respect lock screen/background launch restrictions. |

## Preview milestones

The first preview is an actual running foundation shell, clearly separate from these designs. Phase 1 adds functional technical controls for two-phone testing. Production styling follows the required reliability gate and approved imagery; it is not replaced by the diagnostic shell.

For every future screen milestone, attach a running-app screenshot or demonstration, identify the source reference, record feature/test evidence and list material deviations. Preserve the supplied images and their [SHA-256 inventory](SCREENS_INVENTORY.json).

