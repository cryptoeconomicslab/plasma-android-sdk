package com.cryptoeconomicslab.demo_payment_usecase.ui.new_offers


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.cryptoeconomicslab.demo_payment_usecase.R

/**
 * A simple [Fragment] subclass.
 */
class NewOfferFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_offer, container, false)
    }


}
