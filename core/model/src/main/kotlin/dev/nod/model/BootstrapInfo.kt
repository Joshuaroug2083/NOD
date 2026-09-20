package dev.nod.model

/** Diagnostic build metadata only; this is not a discovered peer or active session. */
data class BootstrapInfo(
    val minimumApi: Int = 26,
    val targetApi: Int = 36,
    val protocolVersion: Int = 1
)
