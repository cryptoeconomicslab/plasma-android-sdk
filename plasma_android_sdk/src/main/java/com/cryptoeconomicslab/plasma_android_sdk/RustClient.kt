package com.cryptoeconomicslab.plasma_android_sdk

import com.cryptoeconomicslab.plasma_android_sdk.hello_world.HelloWorld

class RustClient {

    companion object {
        /**
         * This will load the Rust library.
         * This method should be called once in your application.
         */
        fun initialize() {
            System.loadLibrary("plasma_android_sdk")
        }
    }

    /**
     * Sample method for communicating with Rust layer.
     * @param message String that will be passed to Rust layer.
     * @return String which is modified in Rust layer.
     */
    fun sayHello(message: String): String {
        return HelloWorld().hello(message)
    }
}