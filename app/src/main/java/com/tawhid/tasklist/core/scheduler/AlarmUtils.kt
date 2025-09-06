package com.tawhid.tasklist.core.scheduler

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.google.gson.Gson
import com.tawhid.tasklist.domain.model.TaskModel

const val REMINDER = "REMINDER"
const val NOTIFICATION_CHANNEL_ID = "task_reminder_channel"
const val NOTIFICATION_ID = 1

fun setUpAlarmWithNotification(context: Context, task: TaskModel, reminderTimeMillis: Long) {
    val intent = Intent(context, ReminderReceiver::class.java).apply {
        putExtra(REMINDER, Gson().toJson(task))
    }

    val requestCode = task.id.toInt()
    val pendingIntent = PendingIntent.getBroadcast(
        context,
        requestCode,
        intent,
        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
    )

    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    try {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && !alarmManager.canScheduleExactAlarms()) {
            println("Cannot schedule exact alarms. Please grant permission.")
            return
        }
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, reminderTimeMillis, pendingIntent)
    } catch (e: SecurityException) {
        e.printStackTrace()
        println("SecurityException: Check for SCHEDULE_EXACT_ALARM permission. ${e.message}")
    }
}

fun cancelAlarm(context: Context, task: TaskModel) {
    val intent = Intent(context, ReminderReceiver::class.java).apply {
        putExtra(REMINDER, Gson().toJson(task))
    }

    val requestCode = task.id.toInt()
    val pendingIntent = PendingIntent.getBroadcast(
        context,
        requestCode,
        intent,
        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_NO_CREATE
    )

    if (pendingIntent != null) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        try {
            alarmManager.cancel(pendingIntent)
            pendingIntent.cancel()
            println("Alarm cancelled for task: ${task.title}")
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    } else {
        println("PendingIntent not found. Alarm may not have been set or already cancelled for task: ${task.title}")
    }
}