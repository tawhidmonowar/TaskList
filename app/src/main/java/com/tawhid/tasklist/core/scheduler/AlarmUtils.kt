package com.tawhid.tasklist.core.scheduler

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import com.google.gson.Gson
import android.content.Intent
import com.tawhid.tasklist.domain.model.TaskModel

const val REMINDER = "REMINDER"

fun setUpAlarm(context: Context, reminder: TaskModel) {
    val intent = Intent(context, ReminderReceiver::class.java).apply {
        putExtra(REMINDER, Gson().toJson(reminder))
    }
    val pendingIntent = PendingIntent.getBroadcast(
        context, reminder.reminderTime?.toInt() ?: 0,
        intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
    )
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    try {
        reminder.reminderTime?.let {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, it, pendingIntent)
        }
    } catch (e: SecurityException) {
        e.printStackTrace()
    }
}

fun cancelAlarm(context: Context, reminder: TaskModel) {
    val intent = Intent(context, ReminderReceiver::class.java).apply {
        putExtra(REMINDER, Gson().toJson(reminder))
    }
    val pendingIntent = PendingIntent.getBroadcast(
        context, reminder.reminderTime?.toInt() ?: 0,
        intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
    )
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    try {
        alarmManager.cancel(pendingIntent)
    } catch (e: SecurityException) {
        e.printStackTrace()
    }
}

