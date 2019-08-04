package com.cryptoeconomicslab.plasma_android_sdk

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val client = PlasmaClient()

        findViewById<TextView>(R.id.text).text = client.sayHello("from plasma rust sdk")
    }
}
