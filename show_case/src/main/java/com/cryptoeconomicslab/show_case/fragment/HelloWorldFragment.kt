package com.cryptoeconomicslab.show_case.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.cryptoeconomicslab.plasma_android_sdk.PlasmaClient
import com.cryptoeconomicslab.show_case.R

/**
 * Fragment for Hello World use case
 * This shows String message
 */
class HelloWorldFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_hello_world, container, false)

        val client = PlasmaClient()
        val messageFromRust = client.sayHello("Hello from Rust")

        view.findViewById<TextView>(R.id.text).apply {
            text = messageFromRust
        }

        return view
    }


}
