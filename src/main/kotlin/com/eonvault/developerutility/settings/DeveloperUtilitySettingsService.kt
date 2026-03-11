package com.eonvault.developerutility.settings

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage

private const val DEFAULT_LOG_ANALYSIS_PREVIEW_LINE_LIMIT = 200

enum class StructuredDataFormat {
    JSON,
    YAML,
}

data class DeveloperUtilitySettingsState(
    var defaultStructuredDataFormat: String = StructuredDataFormat.JSON.name,
    var persistLastCommandInput: Boolean = true,
    var logAnalysisPreviewLineLimit: Int = DEFAULT_LOG_ANALYSIS_PREVIEW_LINE_LIMIT
)

data class DeveloperUtilitySettingsSnapshot(
    val defaultStructuredDataFormat: StructuredDataFormat,
    val persistLastCommandInput: Boolean,
    val logAnalysisPreviewLineLimit: Int
)

@Service(Service.Level.APP)
@State(
    name = "DeveloperUtilitySettings",
    storages = [Storage("eonvaultDeveloperUtility.xml")]
)
class DeveloperUtilitySettingsService : PersistentStateComponent<DeveloperUtilitySettingsState> {
    private var state = DeveloperUtilitySettingsState()

    override fun getState(): DeveloperUtilitySettingsState = state

    override fun loadState(state: DeveloperUtilitySettingsState) {
        this.state = state.copy()
    }

    fun currentSettings(): DeveloperUtilitySettingsSnapshot = DeveloperUtilitySettingsSnapshot(
        defaultStructuredDataFormat = parseFormat(state.defaultStructuredDataFormat),
        persistLastCommandInput = state.persistLastCommandInput,
        logAnalysisPreviewLineLimit = state.logAnalysisPreviewLineLimit.coerceAtLeast(1)
    )

    fun updateSettings(snapshot: DeveloperUtilitySettingsSnapshot) {
        state = DeveloperUtilitySettingsState(
            defaultStructuredDataFormat = snapshot.defaultStructuredDataFormat.name,
            persistLastCommandInput = snapshot.persistLastCommandInput,
            logAnalysisPreviewLineLimit = snapshot.logAnalysisPreviewLineLimit.coerceAtLeast(1)
        )
    }

    private fun parseFormat(rawValue: String): StructuredDataFormat =
        StructuredDataFormat.entries.firstOrNull { it.name == rawValue } ?: StructuredDataFormat.JSON
}
