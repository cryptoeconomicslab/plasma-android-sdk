package com.cryptoeconomicslab.plasma_android_sdk.pubsub

import android.util.Log

internal class Client : ClientContract {
    external override fun createAccount(): String
    external override fun getAddress(string: String): String
    external override fun getBalance(string: String): String

    external fun send(endpoint: String, string: String, start: Int, end: Int, to: String): String

}