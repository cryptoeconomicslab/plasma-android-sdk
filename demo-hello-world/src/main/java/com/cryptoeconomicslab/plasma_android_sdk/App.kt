package com.cryptoeconomicslab.plasma_android_sdk

import android.app.Application

/**
 * Custom Application Class
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()

        PlasmaClient.initialize()
    }
}