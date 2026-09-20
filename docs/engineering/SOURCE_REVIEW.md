# Source review and provenance

Reviewed before application code: 20 September 2026.

Updated conversation review later on 20 September: read all newly returned turns back to the previously reviewed handoff, including the user's own-identity-only correction and the proposed 19–56 screen sequence. Image-generation turns contain only the user's `next` text in this tool; generated image pixels are not exposed. The screen list is an inventory of described designs, not proof that all image assets were obtained. Latest explicit product corrections override the preserved historical handoff where they conflict; see ADR-011 and the UI reference register.

The updated tool response also supplied six uploaded attachment paths. Inspected their available visual references and retained four unique byte-preserved files under `docs/design/reference/`, including the portrait splash, master board, annotated splash and standalone logo. `docs/design/ATTACHMENTS.json` records source names, local paths and SHA-256. They supplement the original root assets; they are not a complete export of generated Screens 19–56.

- Re-scanned the entire folder after the user's reminder. `Nod_Codex_Engineering_Handoff/` is now present (absent from the initial file listing). Read every Markdown file: eight root documents, all ten ADRs and their index, security rules, test strategy, protocol draft and conceptual data model. Its explicit Phase 0-only scope supersedes the earlier proposed session-implementation work. Existing handoff content is referenced, not rewritten.
- Read text content of all 30 SVG screens and checked dimensions of every PNG. Conflicts include broad Allow All onboarding, missing code comparison, persistent Nod ID and older always-visible/auto-save copy. Later consent, ephemeral identity and contextual permission decisions supersede these mockups.
- The user subsequently reaffirmed `ChatGPT Image Sep 19, 2026, 09_46_22 PM.png` as the primary UI reference. It was opened and visually inspected, along with the large splash reference and locked logo. Visual direction is authoritative; later safety/permission steps must be incorporated in that design, not used to justify redesigning it. The two large splash PNG files are byte-identical.
- [SOURCE_INVENTORY.json](SOURCE_INVENTORY.json) records paths, sizes and SHA-256 for all 127 supplied source files (documents, handoff and visual assets). PDF/DOCX text comparison found no substantive missing paragraph text in any individual counterpart.

- Read both pages (15 turns, newest through oldest) returned for Android App Planning, conversation `6aacb020-7b2c-83ea-9529-2da8d3edf410`. Earlier naming exploration/Droid Drop is superseded by Nod. Older always-visible guidance is superseded by temporary Quick Nod. Two-way exchange remains a v1 product requirement; the first spike tests one direction, then reverses roles.
- Retrieval limitation: embedded historical attachments render as reference placeholders, not their contents; one long stack message is capped by the conversation tool at 20,000 characters. Do not assert every historical attachment or every character was retrievable. The supplied controlled pack and explicit current user locks provide the implementation baseline.
- Extracted and read all 17 individual DOCX specifications (00–16), including tables in document order. Compared master binder text: additional content is the consolidation introduction/index, not additional product requirements.
- Extracted all 18 PDF counterparts (individual documents plus 42-page binder). Three public-policy PDF copies are byte-identical to their PDF counterparts. Original sources remain unchanged; extraction scratch files are ignored by Git.
- Inspected root README/MANIFEST and brand reference. All 30 PNG/SVG flow files are present. Older generated screens predate the final security/Quick Nod revisions and must not override them.
- Locked logo reference: `Brand/Nod_Locked_Logo_Reference.png`, 166x62 pixels. Retain exact source. No clarity edit is necessary for a text-only engineering harness; production-size/vector asset work is deferred, preserving the branding.

## Requirement mapping

### Later screen package

After the original source review, the user supplied `Nod_All_Screens/`. All 53 files were enumerated and hashed in `docs/design/SCREENS_INVENTORY.json`; its README and contact sheet were inspected, alongside full-resolution named additions 19–27 and the supplementary logo crop. The primary 01–18 board had already been reviewed. This resolves missing local references for 19–27; it does not establish approved screens 28–56 or any implemented product UI. The folder README identifies legacy screen 11, screen 28 needing correction and draft Quick Nod imagery. The screen status table separates design availability from implementation, feature integration and validation. Original files were retained unchanged.

| Sources | Implementation implications |
|---|---|
| 00, 09, 14, 16 | Spike/hardware gates, exact version evidence, no release based on build success alone |
| 01, 02 | Android-only/two-peer/offline, distinct consent, contextual permissions, minimal metadata |
| 03, 04 | Layered module boundaries, transport abstraction, versioned typed messages, payload correlation |
| 05, 06, 07 | Authentication, profile encryption, backup exclusions, no permanent beacon, safe background behavior |
| 08, 12 | Locked branding, accessibility, ownership/licence provenance; original image remains unchanged |
| 10, 11, 13, 15 | Draft operator/legal/support details remain unresolved release work; no policies published by bootstrap |

Unresolved production decisions: legal owner/domain/application ID, non-GMS commitment, age targeting, monetization, history retention default, durable blocking identity, exact Always Ready FGS declaration, verified public-file publication on older APIs. None prevents an isolated Phase 0 scaffold.
