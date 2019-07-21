package com.cryptoeconomicslab.plasma_android_sdk

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RustClient.initialize()

        findViewById<TextView>(R.id.text).text = RustClient.sayHello()

        RustClient.sayHelloFromRust()
    }
}
