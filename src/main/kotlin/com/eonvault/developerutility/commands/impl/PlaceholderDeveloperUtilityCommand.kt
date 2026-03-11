package com.eonvault.developerutility.commands.impl

import com.eonvault.developerutility.commands.DeveloperUtilityCommand
import com.eonvault.developerutility.commands.DeveloperUtilityCommandContext
import com.eonvault.developerutility.commands.DeveloperUtilityCommandDefinition
import com.eonvault.developerutility.commands.DeveloperUtilityCommandOutput
import com.eonvault.developerutility.commands.DeveloperUtilityCommandResult

abstract class PlaceholderDeveloperUtilityCommand(
    final override val definition: DeveloperUtilityCommandDefinition
) : DeveloperUtilityCommand {
    final override fun execute(context: DeveloperUtilityCommandContext): DeveloperUtilityCommandResult<DeveloperUtilityCommandOutput> =
        DeveloperUtilityCommandResult.Success(buildOutput(context))

    protected abstract fun buildOutput(context: DeveloperUtilityCommandContext): DeveloperUtilityCommandOutput
}
