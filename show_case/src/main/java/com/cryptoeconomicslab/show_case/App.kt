package com.cryptoeconomicslab.show_case

import android.app.Application
import com.cryptoeconomicslab.plasma_android_sdk.PlasmaClient

/**
 * Custom Application Class
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()

        PlasmaClient.initialize(applicationContext)
    }
}