package com.mikhailrusin.zennextestapp

import android.app.Application
import com.mikhailrusin.zennextestapp.di.data.dataModule
import com.mikhailrusin.zennextestapp.di.ui.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ZennexApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ZennexApplication)
            modules(listOf(dataModule, uiModule))
        }
    }
}