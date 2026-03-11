package com.eonvault.developerutility.commands

import com.eonvault.developerutility.settings.DeveloperUtilitySettingsSnapshot
import com.eonvault.developerutility.settings.StructuredDataFormat
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

class DeveloperUtilityCommandRegistryTest {
    @Test
    fun `registry exposes expected foundation commands`() {
        val registry = DeveloperUtilityCommandRegistry()

        val definitions = registry.allDefinitions()

        assertEquals(3, definitions.size)
        assertEquals(
            listOf(
                DeveloperUtilityCommandId.ANALYZE_LOGS,
                DeveloperUtilityCommandId.CONVERT_STRUCTURED_DATA,
                DeveloperUtilityCommandId.VALIDATE_STRUCTURED_DATA
            ),
            definitions.map { it.id }
        )
    }

    @Test
    fun `registry fails when project is required but absent`() {
        val registry = DeveloperUtilityCommandRegistry()

        val result = registry.execute(
            commandId = DeveloperUtilityCommandId.CONVERT_STRUCTURED_DATA,
            context = DeveloperUtilityCommandContext(
                project = null,
                source = "test",
                settings = DeveloperUtilitySettingsSnapshot(
                    defaultStructuredDataFormat = StructuredDataFormat.JSON,
                    persistLastCommandInput = true,
                    logAnalysisPreviewLineLimit = 200
                )
            )
        )

        val failure = assertIs<DeveloperUtilityCommandResult.Failure>(result)
        assertEquals(DeveloperUtilityErrorCode.PROJECT_REQUIRED, failure.error.code)
    }
}
