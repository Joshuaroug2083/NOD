package dev.nod.feature.spike

import app.cash.turbine.test
import dev.nod.domain.BuildInfoSource
import dev.nod.model.BootstrapInfo
import dev.nod.testing.MainDispatcherRule
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class SpikeViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun exposesInjectedMetadataWithoutPlatformDependency() = runTest {
        val expected = BootstrapInfo(minimumApi = 27, targetApi = 35, protocolVersion = 2)
        val viewModel = SpikeViewModel(BuildInfoSource { expected })

        viewModel.state.test {
            assertEquals(expected, awaitItem())
            expectNoEvents()
        }
    }
}
