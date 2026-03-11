package com.eonvault.developerutility.commands.impl

import com.eonvault.developerutility.commands.DeveloperUtilityCommandCategory
import com.eonvault.developerutility.commands.DeveloperUtilityCommandContext
import com.eonvault.developerutility.commands.DeveloperUtilityCommandDefinition
import com.eonvault.developerutility.commands.DeveloperUtilityCommandId
import com.eonvault.developerutility.commands.DeveloperUtilityCommandOutput

class ValidateStructuredDataCommand : PlaceholderDeveloperUtilityCommand(
    definition = DeveloperUtilityCommandDefinition(
        id = DeveloperUtilityCommandId.VALIDATE_STRUCTURED_DATA,
        displayName = "Validate Structured Data",
        description = "Validate JSON or YAML documents before use.",
        category = DeveloperUtilityCommandCategory.STRUCTURED_DATA
    )
) {
    override fun buildOutput(context: DeveloperUtilityCommandContext): DeveloperUtilityCommandOutput =
        DeveloperUtilityCommandOutput(
            title = definition.displayName,
            message = "Structured data validation is registered and ready for implementation.",
            details = "Last input persistence: ${context.settings.persistLastCommandInput}."
        )
}
