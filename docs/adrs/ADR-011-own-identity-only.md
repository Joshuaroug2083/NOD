# ADR-011 — Share the user's own identity and files

Status: Accepted by explicit user correction on 20 September 2026.

## Source and authority

The user corrected the product scope in [Android App Planning](chatgpt-conversation://6aacb020-7b2c-83ea-9529-2da8d3edf410), user turn `278816ea-9f94-4a60-99fe-f9d3da23ceaf`:

> "i don't want nod to share other contacts in the users phone just their own contact or professional business card"

The current implementation request explicitly asks that the updated planning conversation inform the app. This ADR records the user's correction, rather than treating the assistant's subsequent suggestions or claim to be "locking" decisions as independent authorization. It supersedes conflicting contact-selection examples in the original handoff, controlled documents and mockups. Those originals remain preserved.

## Decision

Nod v1 shares the user's own locally stored contact identity or professional business card, and files, with another nearby Android user. Real-world networking is a primary use case.

The encrypted local Nod Profile is the source of outgoing identity fields. Existing Quick, Personal and Professional Share Profiles select fields from that same identity. The sender reviews the selected fields before offering them.

Nod does not browse, manage, select or redistribute other people's contacts from the user's phone. Remove Select Contacts, My Contacts, contact search, favorites, groups and address-book lists from the active UX scope. QR/contact-manager screens introduced during image-generation drift are not requirements for this implementation.

Receiving another participant's own card remains supported. After explicit receiver acceptance and successful receipt, the receiver can preview the card and explicitly choose to save it through Android Contacts. Nod must not maintain a shadow address book or automatically save received cards.

## Consent and privacy consequences

- Mutual comparison and explicit approval of the transport authentication code remain mandatory before exchange.
- A connection approval never substitutes for content acceptance. The incoming card offer may describe non-sensitive metadata and field labels; it must not disclose private field values or the profile photo before acceptance.
- Only the exact accepted field selection may transfer. Nearby participants cannot browse or pull another user's profile.
- Do not request broad Contacts read access for the core identity-sharing flow. Prefer an explicit system contact-insertion flow for saving a received card. A duplicate-contact screen does not authorize address-book enumeration; duplicate lookup remains feasibility-gated under this permission constraint.
- Keep the Nod Profile encrypted on-device, backup exclusions intact and history limited to necessary transfer metadata. Discovery continues to expose only the permitted nearby alias and ephemeral endpoint representation.

## Scope and implementation consequences

The production UI must replace the primary reference board's Select Contacts flow with own-profile selection and field review while preserving its visual language and locked logo. See [the UI reference register](../design/UI_REFERENCE.md).

The engineering phase order is unchanged: complete the foundation and physical two-phone transfer spike before polished production UI. Identity exchange belongs to handoff Phase 3; production UI belongs to handoff Phase 6. The planning chat's separate UI "Phase 2" label does not authorize skipping these gates.

Versioned Protobuf, file transfer, two-device sessions, temporary Quick Nod, explicit consent and integrity verification remain unchanged. The transport spike still validates a large test-file transfer before identity features are implemented.

The assistant's subsequent proposals for vCard export, custom cards and a dedicated Exchange Cards action are proposals, not additional requirements accepted by this correction. Existing agreed two-way exchange may still be implemented with independent consent in each direction; this ADR does not approve a new combined interaction or serialization format. Exact optional profile fields and new preset types require their own accepted specification.

Resume, durable blocking and duplicate-contact lookup remain subject to their documented feasibility and privacy gates. Their presence in a screen inventory is not proof of platform support or authorization to relax security or permission boundaries.

## Verification when this feature is implemented

- Outgoing identity selection draws only from the local Nod Profile and its Share Profiles; no address-book browsing or redistribution flow exists.
- Tests prevent private card values from leaving the device before exact-manifest acceptance.
- Saving a received card requires a deliberate receiver action and does not silently create a Nod contacts database.
- Permissions and UI tests confirm that own-card sharing works without broad Contacts read access.
- Updated screens retain the locked visual references and separate verification, acceptance, transfer, verification of received data and completion states.
