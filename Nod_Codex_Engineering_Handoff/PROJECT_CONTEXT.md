# Nod — Project Context

## 1. Product vision

Nod is a native Android application for fast, private, nearby exchange between **two phones**. The product should feel closer to handing something directly to another person than uploading data to a service.

Core phrase:

> Two Android phones. Nearby. No internet. Discover, verify, choose, Nod.

## 2. Initial use cases

### 2.1 Contact exchange

A user maintains a local **Nod Profile**. The profile may contain:

- display name;
- profile image;
- one or more phone numbers;
- one or more email addresses;
- optional company;
- optional job title;
- optional website;
- optional address/social fields in later releases.

The user can create share presets such as:

- Quick: name + primary phone;
- Personal;
- Professional.

Only explicitly selected fields leave the device.

A received contact is previewed before the user decides whether to save it to Android Contacts.

### 2.2 File exchange

Launch file types:

- photos;
- videos;
- documents.

Sender selects and previews. Receiver sees a manifest and explicitly accepts before file bytes transfer.

### 2.3 Number scanner

Nod can use the camera or a selected image to detect typed/handwritten phone numbers locally. Recognition output is reviewed by the user before copying/saving.

### 2.4 Quick Nod

Fast entry methods:

- open Nod normally;
- Quick Settings tile;
- optional Shake ×3 gesture when Always Ready is enabled.

Quick Nod starts a **temporary** discovery/advertising window, initially targeted at 60 seconds.

## 3. Identity model

Nod does not require a cloud account for identity.

`NodProfile` is encrypted in private app storage. It is logically separate from Android Contacts and can optionally import selected data from the user's own contact card.

Important distinction:

- **Nod Profile**: the user's local identity data.
- **Nearby Alias**: minimal name shown during discovery.
- **Android Contacts**: external system address book, only accessed with explicit user action/permission.

## 4. Privacy model

The app should reveal as little as possible before mutual consent.

Discovery advertises no phone number, email, or complete contact card. The receiver must not receive full payload contents before accepting a manifest.

Transfer history should retain minimal metadata, not a shadow copy of contacts/files.

## 5. Session model

A typical session is:

`IDLE → READY → DISCOVERING/ADVERTISING → PEER_FOUND → CONNECTION_REQUESTED → VERIFYING → CONNECTED → CONTENT_PROPOSED → AWAITING_ACCEPTANCE → TRANSFERRING → VERIFYING_PAYLOAD → COMPLETED → DISCONNECTED`

Failure states include:

- permission denied;
- peer lost;
- user rejected;
- authentication mismatch;
- transfer interrupted;
- insufficient storage;
- integrity failure;
- timeout;
- cancellation;
- unsupported protocol version.

## 6. Technical direction

The first transport proof of concept uses Google Nearby Connections with point-to-point strategy. Higher layers must depend only on `NodTransport`.

The protocol is versioned Protobuf. Control messages and file payloads are correlated with explicit identifiers.

## 7. Non-goals for v1

Do not add:

- iOS support;
- desktop support;
- group transfer;
- cloud sync;
- remote internet transfer;
- messaging/chat;
- social feed;
- account/authentication server;
- permanent background discovery;
- automatic acceptance;
- automatic opening/execution of received content.
