package com.cryptoeconomicslab.demo_payment_usecase.ui.home.wallet


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.cryptoeconomicslab.demo_payment_usecase.R
import com.cryptoeconomicslab.demo_payment_usecase.ui.home.ActionBarCallback

/**
 * A simple [Fragment] subclass.
 */
class WalletFragment(private val actionBarCallback: ActionBarCallback) : Fragment() {

    companion object {
        fun getFragment(actionBarCallback: ActionBarCallback) = WalletFragment(actionBarCallback)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        actionBarCallback.setActionBarTitle(getString(R.string.screen__wallet_name))
        actionBarCallback.clearActionBarMenu()

        val view = inflater.inflate(R.layout.fragment_wallet, container, false)

        return view
    }
}
