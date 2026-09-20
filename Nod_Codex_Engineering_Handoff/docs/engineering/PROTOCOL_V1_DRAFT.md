# Nod Protocol v1 — Draft

Status: pre-spike draft. Field numbering must be finalized before release and then treated as compatibility-sensitive.

## Envelope

Illustrative only:

```proto
message NodEnvelope {
  uint32 protocol_version = 1;
  string session_id = 2;
  string message_id = 3;

  oneof body {
    Hello hello = 10;
    Capabilities capabilities = 11;
    TransferManifest transfer_manifest = 12;
    TransferDecision transfer_decision = 13;
    FileBinding file_binding = 14;
    TransferComplete transfer_complete = 15;
    TransferCancel transfer_cancel = 16;
    TransferError transfer_error = 17;
    Ack ack = 18;
  }
}
```

## Handshake

1. Transport connection accepted after human verification.
2. A → B: `Hello`.
3. B → A: `Hello`.
4. Both exchange supported protocol versions/capabilities.
5. Both select compatible version.
6. If none: terminate with `UNSUPPORTED_VERSION`.

## Manifest

A manifest is sent before file payloads.

Each item includes:

```text
item_id
display_name
mime_type
size_bytes
sha256
logical_type (photo/video/document/contact where applicable)
```

Receiver responds accept/reject before file payload begins.

## File binding

A `FileBinding` explicitly maps protocol `item_id` to the transport `payload_id` when required by the adapter. Do not rely on ordering.

## Completion

Receiver verifies all required items before sending `TransferComplete(success=true)`.

Sender's final UI success semantics should be based on the defined completion acknowledgement, not merely local send callback completion.
