package com.eonvault.developerutility.commands

data class DeveloperUtilityCommandDefinition(
    val id: DeveloperUtilityCommandId,
    val displayName: String,
    val description: String,
    val category: DeveloperUtilityCommandCategory,
    val requiresOpenProject: Boolean = true
)
