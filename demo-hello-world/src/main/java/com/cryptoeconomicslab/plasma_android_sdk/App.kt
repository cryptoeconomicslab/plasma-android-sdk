package com.cryptoeconomicslab.plasma_android_sdk

import android.app.Application

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        RustClient.initialize()
    }
}