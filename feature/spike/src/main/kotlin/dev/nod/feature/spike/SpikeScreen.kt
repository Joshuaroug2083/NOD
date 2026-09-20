package dev.nod.feature.spike

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.nod.model.BootstrapInfo

private val BootstrapColors = lightColorScheme(
    primary = Color(0xFFBFFF00),
    onPrimary = Color(0xFF171717),
    background = Color.White,
    onBackground = Color(0xFF171717),
    surface = Color.White,
    onSurface = Color(0xFF171717)
)

@Composable
fun SpikeRoute(viewModel: SpikeViewModel = viewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    SpikeScreen(state)
}

/** Deliberately plain Phase 0 harness, with no simulated connectivity or transfer controls. */
@Composable
fun SpikeScreen(info: BootstrapInfo, modifier: Modifier = Modifier) {
    MaterialTheme(colorScheme = BootstrapColors) {
        Surface(modifier = modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .safeDrawingPadding()
                    .verticalScroll(rememberScrollState())
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    stringResource(R.string.spike_title),
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(stringResource(R.string.bootstrap_status))
                Text(stringResource(R.string.android_baseline, info.minimumApi, info.targetApi))
                Text(stringResource(R.string.protocol_baseline, info.protocolVersion))
                Text(stringResource(R.string.transport_pending))
            }
        }
    }
}
