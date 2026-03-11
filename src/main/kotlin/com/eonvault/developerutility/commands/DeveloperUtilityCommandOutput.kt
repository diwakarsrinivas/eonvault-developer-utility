package com.eonvault.developerutility.commands

data class DeveloperUtilityCommandOutput(
    val title: String,
    val message: String,
    val details: String? = null
)
