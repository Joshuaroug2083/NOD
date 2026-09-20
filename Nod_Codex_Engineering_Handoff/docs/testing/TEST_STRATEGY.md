# Nod — Test Strategy

## 1. Test pyramid

### Unit tests

- session state machine;
- protocol validation;
- capability negotiation;
- transfer manifest validation;
- filename sanitization;
- number normalization;
- progress calculations;
- hash/integrity logic;
- cleanup decisions;
- Quick Nod timer logic;
- shake detector algorithm with synthetic sensor traces.

### Integration tests

- Room migrations/repositories;
- DataStore settings;
- encrypted profile read/write;
- staging/finalization abstraction;
- protocol + transport fake.

### Instrumented/UI tests

- permission-driven states;
- Compose state rendering;
- contact/file picker return handling;
- service/tile entry points where automatable.

### Physical-device tests

Mandatory for transport/background behavior.

## 2. Phase 1 physical matrix

Start small but heterogeneous:

- Samsung device.
- Pixel or another clean Android device.
- Tecno/Infinix/Xiaomi/Redmi class device as soon as available.

Expand before release.

## 3. OS matrix

At minimum include representatives for:

- API 26/27 behavior;
- API 28-30;
- API 31-32;
- API 33;
- API 34;
- API 35;
- API 36.

Preview compatibility may be tested separately and must not block stable release unless product decides otherwise.

## 4. Transfer size matrix

- 1 KB control message.
- 100 KB file.
- 10 MB file.
- 100 MB file.
- 1 GB file.
- multi-GB file where storage permits.

## 5. Failure injection

During transfer:

- cancel sender;
- cancel receiver;
- kill sender process;
- kill receiver process;
- lock screen;
- background app;
- remove connectivity/radio;
- walk out of range;
- run receiver low on storage;
- force hash mismatch;
- duplicate filename;
- invalid manifest;
- unsupported protocol version.

For each case record:

- sender final state;
- receiver final state;
- temp-file cleanup;
- whether any corrupt final file is visible;
- recovery path.

## 6. Performance metrics

Measure rather than promise:

- discovery latency;
- connection setup latency;
- verification-to-ready latency;
- throughput by file size/device pair;
- CPU usage during hashing;
- memory usage;
- battery impact of Always Ready;
- false-positive shake rate.
