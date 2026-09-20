package dev.nod.common

import kotlinx.coroutines.CoroutineDispatcher

/** Inject execution contexts so future I/O and CPU work can be tested deterministically. */
interface NodDispatchers {
    val io: CoroutineDispatcher
    val computation: CoroutineDispatcher
}
