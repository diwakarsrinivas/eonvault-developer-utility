package com.eonvault.developerutility.commands

import com.eonvault.developerutility.settings.DeveloperUtilitySettingsSnapshot
import com.intellij.openapi.project.Project

data class DeveloperUtilityCommandContext(
    val project: Project?,
    val source: String,
    val settings: DeveloperUtilitySettingsSnapshot
)
