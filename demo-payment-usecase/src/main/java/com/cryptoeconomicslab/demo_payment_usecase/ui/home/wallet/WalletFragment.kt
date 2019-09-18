package com.cryptoeconomicslab.demo_payment_usecase.ui.home.wallet


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cryptoeconomicslab.demo_payment_usecase.R
import com.cryptoeconomicslab.demo_payment_usecase.repository.wallet.WalletRepositoryMock
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

        val repository = WalletRepositoryMock()

        val view = inflater.inflate(R.layout.fragment_wallet, container, false)

        view.findViewById<RecyclerView>(R.id.recycler_view).apply {
            adapter = WalletViewAdapter(context, repository.getWallets())
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }


        return view
    }
}
