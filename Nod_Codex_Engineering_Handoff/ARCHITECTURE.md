# Nod — System Architecture

## 1. Architectural style

Nod uses a layered, local-first Android architecture with unidirectional data flow and strict platform boundaries.

```text
Compose UI
   ↓
ViewModels / UI state
   ↓
Domain use cases
   ↓
Repositories / Managers
   ↓
┌───────────────┬───────────────────┬──────────────────┐
│ Local data    │ NodTransport      │ Platform gateways│
│ Room/DataStore│                   │ Files/Contacts   │
└───────────────┴───────────────────┴──────────────────┘
                         ↓
                 NearbyNodTransport
```

## 2. Recommended initial module boundaries

Do not create dozens of Gradle modules before the spike proves the transport. Start with a disciplined but manageable set:

```text
:app
:core:model
:core:common
:core:protocol
:core:security
:domain
:data
:transport:api
:transport:nearby
:platform
:feature:spike
:core:testing
```

After the spike, split feature/platform modules only when code pressure justifies it.

## 3. Core interfaces

### 3.1 Transport

```kotlin
interface NodTransport {
    val events: Flow<TransportEvent>

    suspend fun startAdvertising(config: AdvertisingConfig): Result<Unit>
    suspend fun stopAdvertising(): Result<Unit>
    suspend fun startDiscovery(config: DiscoveryConfig): Result<Unit>
    suspend fun stopDiscovery(): Result<Unit>

    suspend fun requestConnection(peerId: PeerId): Result<Unit>
    suspend fun acceptConnection(requestId: ConnectionRequestId): Result<Unit>
    suspend fun rejectConnection(requestId: ConnectionRequestId): Result<Unit>

    suspend fun sendControl(peerId: PeerId, envelope: ByteArray): Result<Unit>
    suspend fun sendFile(peerId: PeerId, file: OutgoingFile): Result<TransportPayloadId>

    suspend fun cancelPayload(payloadId: TransportPayloadId): Result<Unit>
    suspend fun disconnect(peerId: PeerId): Result<Unit>
}
```

The exact signatures may change after the spike. The abstraction requirement does not.

### 3.2 Session coordinator

`SessionCoordinator` owns the finite state machine. It consumes transport events and emits `SessionState`.

UI never reasons directly about Nearby callbacks.

### 3.3 Transfer manager

Responsibilities:

- create/validate manifests;
- perform storage capacity checks;
- stage incoming files;
- correlate protocol item IDs to transport payload IDs;
- calculate progress;
- verify hashes;
- finalize safely;
- clean temporary files;
- produce transfer audit metadata.

## 4. Protocol architecture

### 4.1 Control plane

Protobuf messages transmitted as small byte payloads.

Minimum envelope fields:

```text
protocol_version
session_id
message_id
message_type/body
sent_at (optional, non-authoritative)
```

Minimum message set for spike:

- `Hello`
- `Capabilities`
- `TransferManifest`
- `TransferAccept`
- `TransferReject`
- `TransferCancel`
- `FileBinding`
- `TransferComplete`
- `TransferFailed`
- `Ack`

### 4.2 Data plane

File payloads use the transport file mechanism. Every file maps to:

- transfer ID;
- item ID;
- transport payload ID;
- expected size;
- expected SHA-256;
- staging path/URI;
- final destination.

Never infer mapping from arrival order.

## 5. Persistence

### 5.1 Room

Use for:

- encrypted Nod Profile metadata/records;
- share profile configuration;
- transfer history metadata;
- blocked peers;
- resumable/incomplete transfer metadata if supported;
- trusted-peer metadata if such a feature is explicitly approved later.

Do not store raw files or giant blobs in Room.

### 5.2 Proto DataStore

Use for:

- onboarding complete;
- nearby alias;
- Quick Nod timeout;
- shake enabled/sensitivity;
- Always Ready enabled;
- UI/preferences.

Sensitive identity fields do not belong here unencrypted.

## 6. File storage lifecycle

Incoming file lifecycle:

```text
manifest accepted
    ↓
private staging allocation
    ↓
stream/write payload
    ↓
size validation
    ↓
SHA-256 validation
    ↓
final destination write/move
    ↓
completion acknowledgement
    ↓
staging cleanup
```

Failure at any point must not create a misleading completed user file.

## 7. Quick Nod architecture

```text
ShakeDetector ─┐
               ├─> StartQuickNodSession use case
TileService ───┤
Home action ───┘
                    ↓
             SessionCoordinator
                    ↓
       advertise + discover (timed)
```

Always Ready keeps only the minimal readiness mechanism necessary for the enabled launch behavior. It does not continuously advertise/discover.

## 8. Threading

- Main dispatcher: UI only.
- I/O dispatcher: hashing, filesystem, database, content resolver operations.
- Transport callbacks are adapted into Flows/channels and immediately leave callback-style APIs.
- Large file hashing and reads must be incremental/streaming.

## 9. Error taxonomy

Domain errors must be typed, not generic strings.

Suggested top-level categories:

- `PermissionError`
- `DiscoveryError`
- `ConnectionError`
- `VerificationError`
- `ProtocolError`
- `StorageError`
- `TransferError`
- `IntegrityError`
- `CancellationError`
- `UnsupportedVersionError`

Each user-facing error maps from a typed domain error to UX copy/actions.
