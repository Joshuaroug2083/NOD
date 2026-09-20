package dev.nod.domain

import dev.nod.model.BootstrapInfo

/** Phase 0 injection seam. It has no transport or profile side effects. */
fun interface BuildInfoSource {
    fun read(): BootstrapInfo
}
