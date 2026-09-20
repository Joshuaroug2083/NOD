# Nod — Core Data Model

This is a conceptual model, not a final Room schema.

## NodProfile

```text
profileId
nearbyAlias
displayName
avatarRef?
phones[]
emails[]
company?
jobTitle?
website?
shareProfiles[]
createdAt
updatedAt
```

Sensitive fields are encrypted at rest.

## ShareProfile

```text
shareProfileId
name
includedFieldRefs[]
isDefault
```

## Peer

```text
peerId (ephemeral transport/domain identifier)
nearbyAlias
capabilities
```

Do not model phone/email as discovery identity.

## Session

```text
sessionId
peerId
negotiatedProtocolVersion
negotiatedCapabilities
state
startedAt
endedAt?
```

## Transfer

```text
transferId
sessionId
direction
status
itemCount
totalBytes
transferredBytes
createdAt
completedAt?
failureCode?
```

## TransferItem

```text
itemId
transferId
displayName
mimeType
sizeBytes
sha256
transportPayloadId?
stagingRef?
finalRef?
status
```

## BlockedPeer

Store only the minimum stable identifier that the chosen transport/security design can safely support. Do not invent a persistent hardware identity if the transport does not provide one safely.
