package com.cryptoeconomicslab.show_case.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.cryptoeconomicslab.plasma_android_sdk.PlasmaClient
import com.cryptoeconomicslab.show_case.R
import kotlin.random.Random

/**
 * Fragment for DB use case
 * This shows db feature
 */
class DBFragment : Fragment() {

    private lateinit var textView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_db, container, false)

        val client = PlasmaClient()

        textView = view.findViewById(com.cryptoeconomicslab.plasma_android_sdk.R.id.text)

        view.findViewById<Button>(R.id.get_all_button).setOnClickListener {
            val entities = client.findAll()

            var message = ""
            entities.forEach {
                message += "${it.id} : ${it.str}\n"
            }

            textView.text = message
        }

        view.findViewById<Button>(R.id.add_button).setOnClickListener {
            client.insertFromRust(generateRandomString())
        }

        return view
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
