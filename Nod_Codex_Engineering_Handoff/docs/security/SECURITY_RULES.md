# Nod — Security Rules and Threat Boundaries

## Assets to protect

- Nod Profile fields.
- received/sent content.
- connection verification state.
- encryption keys.
- transfer integrity.
- user's nearby identity/privacy.

## Primary threats

- malicious nearby peer impersonation;
- spam connection attempts;
- accepting content from the wrong peer;
- tampered or truncated payload;
- corrupt partial files shown as complete;
- sensitive profile disclosure during discovery;
- log leakage;
- unsafe backup of local identity data;
- malicious filename/path manipulation;
- denial of service via repeated requests/huge manifests;
- storage exhaustion;
- protocol downgrade/version confusion.

## Mandatory mitigations

### Peer verification

First connection requires human verification of the same authentication code/token on both devices. A mere successful transport connection is not enough.

### Discovery minimization

Advertise only a minimal nearby alias/temporary endpoint representation. Do not broadcast sensitive profile fields.

### Manifest validation

Reject manifests with:

- impossible/negative sizes;
- unsupported counts beyond configured limits;
- dangerous filenames/path traversal;
- malformed MIME metadata;
- unsupported protocol versions;
- resource demands beyond device capacity.

### Filesystem safety

Normalize/sanitize display filenames. Never use remote filenames as arbitrary paths. Final paths are chosen by local storage policy.

### Integrity

SHA-256 is required for each item in the initial protocol design. A mismatch means failure.

### Sensitive storage

Nod Profile encryption keys are protected by Android Keystore. Sensitive profile records are excluded from ordinary cloud backup.

### Logging

Sensitive user content never enters production logs.

## Abuse controls for later production phase

- request rate limiting;
- temporary mute;
- blocklist;
- limited discoverability window;
- no automatic acceptance;
- optional user-visible nearby alias only.
