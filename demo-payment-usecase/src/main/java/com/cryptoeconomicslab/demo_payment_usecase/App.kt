package com.cryptoeconomicslab.demo_payment_usecase

import android.app.Application
import com.cryptoeconomicslab.plasma_android_sdk.PlasmaClient

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        // PlasmaClient.initialize(this)
    }
}