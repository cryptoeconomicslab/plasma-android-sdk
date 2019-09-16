package com.cryptoeconomicslab.show_case.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.cryptoeconomicslab.plasma_android_sdk.PlasmaClient
import com.cryptoeconomicslab.show_case.R

/**
 * Fragment for Network use case
 * This shows network feature
 */
class NetworkFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_network, container, false)

        val client = PlasmaClient()

        view.findViewById<Button>(R.id.add_button).setOnClickListener {
            client.listen_client()
        }

        return view
    }
}
