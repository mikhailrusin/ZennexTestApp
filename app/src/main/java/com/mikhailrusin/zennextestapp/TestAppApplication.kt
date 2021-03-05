package com.mikhailrusin.zennextestapp

import android.app.Application
import com.mikhailrusin.zennextestapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TestAppApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@TestAppApplication)
            modules(appModule)
        }
    }
}