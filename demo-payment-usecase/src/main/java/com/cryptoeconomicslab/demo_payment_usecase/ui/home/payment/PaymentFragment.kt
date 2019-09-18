package com.cryptoeconomicslab.demo_payment_usecase.ui.home.payment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cryptoeconomicslab.demo_payment_usecase.R
import com.cryptoeconomicslab.demo_payment_usecase.repository.payment.PaymentRepositoryMock
import com.cryptoeconomicslab.demo_payment_usecase.ui.home.ActionBarCallback
import com.cryptoeconomicslab.demo_payment_usecase.ui.home.Transition

/**
 * A simple [Fragment] subclass.
 */
class PaymentFragment(
    private val actionBarCallback: ActionBarCallback,
    private val transition: Transition
) : Fragment() {

    companion object {
        fun getFragment(
            actionBarCallback: ActionBarCallback,
            transition: Transition
        ) = PaymentFragment(actionBarCallback, transition)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        actionBarCallback.setActionBarTitle(getString(R.string.screen__payment_name))
        actionBarCallback.clearActionBarMenu()

        val repository = PaymentRepositoryMock()

        val view = inflater.inflate(R.layout.fragment_payment, container, false)

        view.findViewById<Button>(R.id.add_button).apply {
            setOnClickListener {
                transition.moveToNewPaymentScreen()
            }
        }

        view.findViewById<RecyclerView>(R.id.recycler_view).apply {
            adapter = PaymentViewAdapter(context, repository.getPaymentHistories())
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        return view
    }
}
