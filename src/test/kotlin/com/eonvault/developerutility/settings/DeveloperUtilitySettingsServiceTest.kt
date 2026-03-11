package com.eonvault.developerutility.settings

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class DeveloperUtilitySettingsServiceTest {
    @Test
    fun `service exposes stable default settings`() {
        val service = DeveloperUtilitySettingsService()

        val settings = service.currentSettings()

        assertEquals(StructuredDataFormat.JSON, settings.defaultStructuredDataFormat)
        assertTrue(settings.persistLastCommandInput)
        assertEquals(200, settings.logAnalysisPreviewLineLimit)
    }

    @Test
    fun `service normalizes invalid persisted values`() {
        val service = DeveloperUtilitySettingsService()
        service.loadState(
            DeveloperUtilitySettingsState(
                defaultStructuredDataFormat = "INVALID",
                persistLastCommandInput = false,
                logAnalysisPreviewLineLimit = 0
            )
        )

        val settings = service.currentSettings()

        assertEquals(StructuredDataFormat.JSON, settings.defaultStructuredDataFormat)
        assertEquals(false, settings.persistLastCommandInput)
        assertEquals(1, settings.logAnalysisPreviewLineLimit)
    }
}
