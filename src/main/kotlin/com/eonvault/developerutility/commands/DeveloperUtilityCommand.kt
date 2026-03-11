package com.eonvault.developerutility.commands

interface DeveloperUtilityCommand {
    val definition: DeveloperUtilityCommandDefinition

    fun execute(context: DeveloperUtilityCommandContext): DeveloperUtilityCommandResult<DeveloperUtilityCommandOutput>
}
