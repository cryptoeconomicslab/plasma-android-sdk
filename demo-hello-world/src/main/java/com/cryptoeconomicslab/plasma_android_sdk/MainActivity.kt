package com.cryptoeconomicslab.plasma_android_sdk

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val client = PlasmaClient()

        textView = findViewById(R.id.text)

        findViewById<Button>(R.id.get_all_button).setOnClickListener {
            val entities = client.findAll()

            var message = ""
            entities.forEach {
                message += "${it.id} : ${it.str}\n"
            }

            textView.text = message
        }

        findViewById<Button>(R.id.add_button).setOnClickListener {
            client.insertFromRust(generateRandomString())
        }
    }

    private fun generateRandomString(): String {
        val chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
        var passWord = ""
        for (i in 0..10) {
            passWord += chars[Random.nextInt(0, chars.length)]
        }
        return passWord
    }
}
