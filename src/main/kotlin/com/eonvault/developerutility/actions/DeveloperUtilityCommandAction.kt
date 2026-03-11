package com.eonvault.developerutility.actions

import com.eonvault.developerutility.commands.DeveloperUtilityCommandContext
import com.eonvault.developerutility.commands.DeveloperUtilityCommandId
import com.eonvault.developerutility.commands.DeveloperUtilityCommandRegistry
import com.eonvault.developerutility.notifications.DeveloperUtilityNotifier
import com.eonvault.developerutility.settings.DeveloperUtilitySettingsService
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.components.service

abstract class DeveloperUtilityCommandAction(
    private val commandId: DeveloperUtilityCommandId
) : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        val settings = service<DeveloperUtilitySettingsService>().currentSettings()
        val result = service<DeveloperUtilityCommandRegistry>().execute(
            commandId = commandId,
            context = DeveloperUtilityCommandContext(
                project = event.project,
                source = event.place,
                settings = settings
            )
        )

        DeveloperUtilityNotifier.notify(
            project = event.project,
            result = result
        )
    }
}
