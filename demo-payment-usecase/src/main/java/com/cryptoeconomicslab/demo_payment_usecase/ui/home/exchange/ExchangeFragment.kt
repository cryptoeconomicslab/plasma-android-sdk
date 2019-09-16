package com.cryptoeconomicslab.demo_payment_usecase.ui.home.exchange


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.cryptoeconomicslab.demo_payment_usecase.R
import com.cryptoeconomicslab.demo_payment_usecase.ui.home.ActionBarCallback
import com.cryptoeconomicslab.demo_payment_usecase.ui.home.Transition

/**
 * A simple [Fragment] subclass.
 */
class ExchangeFragment(
    private val actionBarCallback: ActionBarCallback,
    private val transition: Transition
) : Fragment() {

    companion object {
        fun getFragment(
            actionBarCallback: ActionBarCallback,
            transition: Transition
        ) = ExchangeFragment(actionBarCallback, transition)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        actionBarCallback.setActionBarTitle(getString(R.string.screen__offer_name))
        actionBarCallback.clearActionBarMenu()

        val view = inflater.inflate(R.layout.fragment_exchange, container, false)

        view.findViewById<Button>(R.id.add_button).apply {
            setOnClickListener {
                transition.moveToNewOfferScreen()
            }
        }

        return view
    }
}
