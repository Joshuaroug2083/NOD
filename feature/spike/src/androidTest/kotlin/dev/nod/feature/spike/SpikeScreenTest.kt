package dev.nod.feature.spike

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import dev.nod.model.BootstrapInfo
import org.junit.Rule
import org.junit.Test

class SpikeScreenTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun explainsThatConnectivityIsNotImplemented() {
        composeRule.setContent { SpikeScreen(BootstrapInfo()) }

        composeRule.onNodeWithText("Phase 0: build foundation").assertIsDisplayed()
        composeRule.onNodeWithText(
            "Nearby connectivity and file transfer are not implemented yet. No discovery is active."
        ).assertIsDisplayed()
    }
}
