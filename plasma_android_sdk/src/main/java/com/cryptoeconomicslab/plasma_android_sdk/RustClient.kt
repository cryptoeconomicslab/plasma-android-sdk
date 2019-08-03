package com.cryptoeconomicslab.plasma_android_sdk

import com.cryptoeconomicslab.plasma_android_sdk.hello_world.HelloWorld

class RustClient {

    companion object {
        fun initialize() {
            System.loadLibrary("plasma_android_sdk")
        }
    }

    fun sayHello(message: String): String {
        return HelloWorld().hello(message)
    }
}