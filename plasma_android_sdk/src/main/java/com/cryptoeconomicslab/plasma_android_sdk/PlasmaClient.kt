package com.cryptoeconomicslab.plasma_android_sdk

import android.content.Context
import android.util.Log
import com.cryptoeconomicslab.plasma_android_sdk.common.ContextProvider
import com.cryptoeconomicslab.plasma_android_sdk.database.DatabaseClient
import com.cryptoeconomicslab.plasma_android_sdk.pubsub.Client
import com.cryptoeconomicslab.plasma_android_sdk.database.DatabaseExecutor
import com.cryptoeconomicslab.plasma_android_sdk.database.entity.SimpleEntity
import com.cryptoeconomicslab.plasma_android_sdk.hello_world.HelloWorld

/**
 * Client for communication with Rust layer.
 */
class PlasmaClient {

    companion object {
        /**
         * This will load the Rust library.
         * This method should be called once in your application.
         */
        fun initialize(context: Context) {
            System.loadLibrary("plasma_android_sdk")
            ContextProvider.init(context)
        }
    }

    /**
     * [HelloWorld feature]
     * Sample method for communicating with Rust layer.
     * @param message String that will be passed to Rust layer.
     * @return String which is modified in Rust layer.
     */
    fun sayHello(message: String): String {
        return HelloWorld().hello(message)
    }

    /**
     * [Database feature]
     * This method allows you to find all the items in SQLite database
     * @return saved entities, empty when nothing is saved.
     */
    fun findAll(): List<SimpleEntity> {
        return DatabaseClient().findAll()
    }

    // TODO : delete these method since these are method for demo

    /**
     * [Database function]
     * This method will invoke rust method to write to SQLite
     *
     * @param string data to write
     */
    fun insertFromRust(string: String) {
        DatabaseClient().insertFromRust(string, DatabaseExecutor)
    }

    fun listen_client() {
        Log.d("listen_client", Client().listen())
    }
}