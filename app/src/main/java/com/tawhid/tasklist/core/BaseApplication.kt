package com.tawhid.tasklist.core

import android.app.Application
import com.tawhid.tasklist.core.di.initKoin
import org.koin.android.ext.koin.androidContext

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@BaseApplication)
        }
    }
}