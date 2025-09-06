package com.tawhid.tasklist.core.scheduler

import android.Manifest.permission.POST_NOTIFICATIONS
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import com.tawhid.tasklist.R
import com.tawhid.tasklist.core.CHANNEL
import com.tawhid.tasklist.domain.model.TaskModel
import com.tawhid.tasklist.domain.usecase.UpdateReminderUseCase
import kotlinx.coroutines.runBlocking
import org.koin.java.KoinJavaComponent.inject

class ReminderReceiver : android.content.BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val taskJson = intent.getStringExtra(REMINDER)
        if (taskJson != null) {
            val task = Gson().fromJson(taskJson, TaskModel::class.java)
            // Create and show the notification
            createNotificationChannel(context)
            showNotification(context, task)
        }
    }

    private fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Task Reminders"
            val descriptionText = "Channel for task reminder notifications"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(NOTIFICATION_CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun showNotification(context: Context, task: TaskModel) {
        val resultIntent = Intent(context, /* Your MainActivity or relevant Activity */
            com.tawhid.tasklist.core.MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val resultPendingIntent: PendingIntent? = PendingIntent.getActivity(
            context,
            task.id.toInt(),
            resultIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground) // Replace with your notification icon
            .setContentTitle("Task Reminder: ${task.title}")
            .setContentText(task.description ?: "Don't forget about your task!")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true) // Dismiss notification when tapped
            .setContentIntent(resultPendingIntent) // Set the intent that will fire when the user taps the notification
            .setDefaults(NotificationCompat.DEFAULT_ALL) // Use default sound, vibration, etc.
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC) // Show on lock screen


        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Use a unique ID for each notification if you want to show multiple notifications
        // Or use a fixed ID if you want to update the same notification
        val notificationIdForTask = task.id.toInt() // Or some other unique ID from the task

        notificationManager.notify(notificationIdForTask, builder.build())
    }
}