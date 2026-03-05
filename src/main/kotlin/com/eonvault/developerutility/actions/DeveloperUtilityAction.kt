package com.eonvault.developerutility.actions

import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class DeveloperUtilityAction : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        val project = event.project ?: return
        NotificationGroupManager.getInstance()
            .getNotificationGroup("Eonvault Developer Utility")
            .createNotification(
                "Eonvault Developer Utility",
                "Scaffold initialized. Next: implement JSON/YAML and log commands.",
                NotificationType.INFORMATION
            )
            .notify(project)
    }
}
