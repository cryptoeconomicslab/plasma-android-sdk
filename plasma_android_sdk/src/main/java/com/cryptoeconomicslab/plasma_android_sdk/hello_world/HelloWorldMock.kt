package com.cryptoeconomicslab.plasma_android_sdk.hello_world

internal class HelloWorldMock : HelloWorldContract {
    override fun hello(string: String): String {
        return "Hello World"
    }
}