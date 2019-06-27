package com.cryptoeconomicslab.plasma_android_sdk

object RustClient {

    fun initialize() {
        System.loadLibrary("plasma_android_sdk")
    }

    fun sayHello(): String {
        return hello("from plasma rust sdk")
    }
}