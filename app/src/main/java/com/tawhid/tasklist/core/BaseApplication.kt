package com.tawhid.tasklist.core

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import com.tawhid.tasklist.core.di.initKoin
import org.koin.android.ext.koin.androidContext

const val NOTIFICATION_CHANNEL_ID = "NOTIFICATION_CHANNEL_ID"
const val NOTIFICATION_CHANNEL_NAME = "NOTIFICATION_CHANNEL_NAME"

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val notificationChannel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            NOTIFICATION_CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        )
        val notificationManager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(notificationChannel)

        initKoin {
            androidContext(this@BaseApplication)
        }
    }
}