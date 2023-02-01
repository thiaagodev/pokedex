package com.thiaagodev.pokedex

import android.app.Application
import com.thiaagodev.pokedex.di.module
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@Application)
            modules(listOf(module))
        }
    }
}