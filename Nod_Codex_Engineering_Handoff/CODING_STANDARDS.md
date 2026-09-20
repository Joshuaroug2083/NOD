# Nod — Coding Standards

## Kotlin

- Use idiomatic Kotlin and official Kotlin coding conventions.
- Prefer `val` over `var`.
- Prefer immutable collections/models at boundaries.
- Do not use nullable state where a sealed state can model meaning explicitly.
- Prefer value classes for identifiers when practical.

Example:

```kotlin
@JvmInline value class SessionId(val value: String)
@JvmInline value class TransferId(val value: String)
@JvmInline value class PeerId(val value: String)
```

## Coroutines

- No `GlobalScope`.
- Coroutine scopes must have an owner and lifecycle.
- Inject dispatchers or wrap them where domain tests require determinism.
- Convert callback APIs at the adapter boundary.
- Cancellation is part of correctness; never treat it as an unexpected exception.

## Flow and UI state

- Expose immutable `StateFlow`/`Flow`.
- ViewModels receive use cases/repositories, not platform clients.
- Compose functions receive state + events wherever possible.
- Business state belongs outside composables.

## Errors

Bad:

```kotlin
Result.failure(Exception("transfer failed"))
```

Preferred:

```kotlin
sealed interface TransferFailure {
    data object PeerDisconnected : TransferFailure
    data object InsufficientStorage : TransferFailure
    data class IntegrityMismatch(val itemId: ItemId) : TransferFailure
}
```

Map typed failures to user copy in the presentation layer.

## Logging

Allowed:

- event type;
- anonymized/ephemeral IDs if necessary;
- durations;
- byte counts;
- state transitions;
- error categories.

Forbidden:

- phone numbers;
- email addresses;
- contact payloads;
- raw OCR contents;
- authentication codes;
- cryptographic keys;
- file contents;
- sensitive user-entered profile data.

## Files

- Stream; do not load whole large files into memory.
- Use buffered reads/writes.
- Always close descriptors/streams safely.
- Stage incomplete files privately.
- Use deterministic cleanup paths.
- Hash incrementally.
- Never silently overwrite a user file.

## Protobuf

- Never reuse field numbers.
- Reserve removed field numbers/names.
- Additive backward-compatible evolution is preferred.
- Every envelope includes protocol version.
- Unknown messages/features must fail safely or be ignored only when the protocol explicitly allows it.

## Compose

- Build reusable Nod components and design tokens.
- Do not hardcode the brand palette repeatedly across screens.
- Support font scaling and talkback semantics.
- Minimum touch target guidance must be respected.
- Animations must respect reduced-motion/accessibility choices where Android exposes them.

## Git/PR discipline

Preferred commit/PR slices are vertical and reviewable.

Each PR summary must state:

- problem;
- approach;
- architectural impact;
- tests run;
- known limitations;
- screenshots/logs only when they contain no sensitive data.
