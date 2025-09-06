package com.tawhid.tasklist

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import com.tawhid.tasklist.core.di.initKoin
import org.koin.android.ext.koin.androidContext

const val CHANNEL = "task_notification_channel"
const val NAME = "task_notification_name"

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@BaseApplication)
        }
        val notificationChannel = NotificationChannel(CHANNEL, NAME, NotificationManager.IMPORTANCE_DEFAULT)
        val notificationManager = this.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(notificationChannel)
    }
}