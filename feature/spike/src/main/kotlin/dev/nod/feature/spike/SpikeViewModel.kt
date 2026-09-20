package dev.nod.feature.spike

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.nod.domain.BuildInfoSource
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class SpikeViewModel @Inject constructor(buildInfoSource: BuildInfoSource) : ViewModel() {
    private val mutableState = MutableStateFlow(buildInfoSource.read())
    val state = mutableState.asStateFlow()
}
