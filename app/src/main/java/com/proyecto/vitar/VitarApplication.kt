package com.proyecto.vitar

import android.app.Application
import com.proyecto.vitar.di.AppContainer

class VitarApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(this)
    }
}
