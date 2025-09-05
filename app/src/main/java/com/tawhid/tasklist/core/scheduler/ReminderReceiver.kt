package com.tawhid.tasklist.core.scheduler

import android.Manifest.permission.POST_NOTIFICATIONS
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import com.tawhid.tasklist.R
import com.tawhid.tasklist.core.NOTIFICATION_CHANNEL_ID
import com.tawhid.tasklist.domain.model.TaskModel
import com.tawhid.tasklist.domain.usecase.ReminderUpdateUseCase
import kotlinx.coroutines.runBlocking

const val DONE = "DONE"
const val REJECT = "REJECT"

class ReminderReceiver(
    private val reminderUpdateUseCase: ReminderUpdateUseCase
) : BroadcastReceiver() {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onReceive(context: Context, intent: Intent) {

        mediaPlayer = MediaPlayer.create(context, R.raw.alarm_music)

        val reminderJson = intent.getStringExtra(REMINDER)
        val reminder = Gson().fromJson(reminderJson, TaskModel::class.java)

        val doneIntent = Intent(context, ReminderReceiver::class.java).apply {
            putExtra(REMINDER, reminderJson)
            action = DONE
        }
        val donePendingIntent = PendingIntent.getBroadcast(
            context, reminder.reminderTime?.toInt() ?: 0, doneIntent, PendingIntent.FLAG_IMMUTABLE
        )

        val closeIntent = Intent(context, ReminderReceiver::class.java).apply {
            putExtra(REMINDER, reminderJson)
            action = REJECT
        }
        val closePendingIntent = PendingIntent.getBroadcast(
            context, reminder.reminderTime?.toInt() ?: 0, closeIntent, PendingIntent.FLAG_IMMUTABLE
        )

        when (intent.action) {
            DONE -> {
                runBlocking { reminderUpdateUseCase.invoke(reminder.copy(isReminderSet = true)) }
                cancelAlarm(context, reminder)
            }

            REJECT -> {
                runBlocking { reminderUpdateUseCase .invoke(reminder.copy(isReminderSet = true)) }
                cancelAlarm(context, reminder)
            }

            else -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    if (ContextCompat.checkSelfPermission(
                            context,
                            POST_NOTIFICATIONS
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        val notification = NotificationCompat.Builder(
                            context,
                            NOTIFICATION_CHANNEL_ID
                        )
                            .setSmallIcon(R.drawable.ic_launcher_background)
                            .setContentTitle("Task Reminder")
                            .setContentText(reminder.title.plus(" ${reminder.description}"))
                            .addAction(R.drawable.ic_check, "Done", donePendingIntent)
                            .addAction(R.drawable.ic_close, "Close", closePendingIntent)
                            .build()
                        NotificationManagerCompat.from(context)
                            .notify(1, notification)
                    }
                } else {
                    val notification = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle("Task Reminder")
                        .setContentText(reminder.title.plus(" ${reminder.description}"))
                        .addAction(R.drawable.ic_check, "Done", donePendingIntent)
                        .addAction(R.drawable.ic_close, "Close", closePendingIntent)
                        .build()

                    NotificationManagerCompat.from(context)
                        .notify(1, notification)

                }
                mediaPlayer.setOnCompletionListener {
                    mediaPlayer.release()
                }
                mediaPlayer.start()
            }
        }
    }
}