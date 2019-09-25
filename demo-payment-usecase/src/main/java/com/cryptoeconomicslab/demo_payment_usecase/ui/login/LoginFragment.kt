package com.cryptoeconomicslab.demo_payment_usecase.ui.login


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.cryptoeconomicslab.demo_payment_usecase.R
import com.cryptoeconomicslab.demo_payment_usecase.repository.wallet.WalletRepositoryImpl

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment(private val transition: Transition) : Fragment() {

    companion object {
        fun getFragment(transition: Transition) = LoginFragment(transition)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        view.findViewById<Button>(R.id.create_wallet_button).apply {
            setOnClickListener {
                val repository = WalletRepositoryImpl(context)
                val res = repository.createAccount()
                Log.d("LOGIN", res.toString())
                repository.createAccount()?.let {
                    Log.d("LOGIN", "SUCCESS FETCHING")
                    transition.moveToHome()
                    transition.finishScreen()
                }
                // TODO: implement fail to create account message
           }
        }

        return view
    }


}
