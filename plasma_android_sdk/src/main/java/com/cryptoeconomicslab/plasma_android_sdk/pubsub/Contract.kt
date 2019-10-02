package com.cryptoeconomicslab.plasma_android_sdk.pubsub

internal interface ClientContract {
    fun createAccount(): String
    fun getBalance(string: String): String
}