package com.cryptoeconomicslab.plasma_android_sdk

import android.util.Log

object RustClient {

    fun initialize() {
        System.loadLibrary("plasma_android_sdk")
    }

    fun sayHello(): String {
        return hello("from plasma rust sdk")
    }

    fun sayHelloFromRust() {
        val obj = CallBackClass()
        helloFromRust(3, obj)
    }
}

class CallBackClass {
    fun callBack(num: Int) {
        Log.d("PLASMA-SDK-DEBUG", num.toString())
    }
}