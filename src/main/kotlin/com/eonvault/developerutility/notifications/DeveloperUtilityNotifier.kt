package com.eonvault.developerutility.notifications

import com.eonvault.developerutility.commands.DeveloperUtilityCommandOutput
import com.eonvault.developerutility.commands.DeveloperUtilityCommandResult
import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project

object DeveloperUtilityNotifier {
    private const val NOTIFICATION_GROUP_ID = "eonvault.developer.utility.notifications"
    private const val DEFAULT_TITLE = "Eonvault Developer Utility"

    fun notify(
        project: Project?,
        result: DeveloperUtilityCommandResult<DeveloperUtilityCommandOutput>
    ) {
        val notification = when (result) {
            is DeveloperUtilityCommandResult.Success -> createNotification(
                title = result.value.title,
                message = listOfNotNull(result.value.message, result.value.details)
                    .joinToString(separator = "\n"),
                type = NotificationType.INFORMATION
            )

            is DeveloperUtilityCommandResult.Failure -> createNotification(
                title = DEFAULT_TITLE,
                message = listOfNotNull(result.error.message, result.error.details)
                    .joinToString(separator = "\n"),
                type = if (result.error.recoverable) {
                    NotificationType.WARNING
                } else {
                    NotificationType.ERROR
                }
            )
        }

        notification.notify(project)
    }

    private fun createNotification(
        title: String,
        message: String,
        type: NotificationType
    ) = NotificationGroupManager.getInstance()
        .getNotificationGroup(NOTIFICATION_GROUP_ID)
        .createNotification(title, message, type)
}
