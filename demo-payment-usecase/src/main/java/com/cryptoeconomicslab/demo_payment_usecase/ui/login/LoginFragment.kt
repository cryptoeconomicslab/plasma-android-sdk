package com.cryptoeconomicslab.demo_payment_usecase.ui.login


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.cryptoeconomicslab.demo_payment_usecase.R

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
                transition.moveToHome()
                transition.finishScreen()
            }
        }

        return view
    }


}
