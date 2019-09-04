package com.cryptoeconomicslab.plasma_android_sdk.pubsub

import android.util.Log

internal class Client : ClientContract {
    override fun listen(): String {
        Log.e("Client.listen", "Message")
        return listen("Hello")
    }

    external fun listen(string: String): String

}