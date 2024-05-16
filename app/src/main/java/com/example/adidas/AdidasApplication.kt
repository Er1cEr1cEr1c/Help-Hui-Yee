package com.example.adidas

import android.app.Application
import com.example.adidas.data.AppContainer
import com.example.adidas.data.AppDataContainer
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AdidasApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}