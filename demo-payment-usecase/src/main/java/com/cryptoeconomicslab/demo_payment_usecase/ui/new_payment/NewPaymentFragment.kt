package com.cryptoeconomicslab.demo_payment_usecase.ui.new_payment


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.cryptoeconomicslab.demo_payment_usecase.R
import com.cryptoeconomicslab.demo_payment_usecase.repository.payment.PaymentRepositoryImpl
import com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity.Payment

/**
 * A simple [Fragment] subclass.
 */
class NewPaymentFragment(val transition: Transition) : Fragment() {

    lateinit var progressBar: ProgressBar
    lateinit var overlayView: View

    lateinit var amountText: EditText
    lateinit var addressText: EditText

    companion object {
        fun getFragment(transition: Transition) = NewPaymentFragment(transition)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_new_payment, container, false)

        progressBar = view.findViewById(R.id.progress_bar)
        overlayView = view.findViewById(R.id.overlay)

        amountText = view.findViewById(R.id.amount_text_field)
        addressText = view.findViewById(R.id.target_text)

        view.findViewById<Button>(R.id.send_button).apply {
            setOnClickListener {
                showConfirmDialog(
                    context,
                    amountText.text.toString().toInt(),
                    addressText.text.toString()
                )
            }
        }

        overlayView.setOnClickListener {
            context?.let {
                showCompletedDialog(
                    it,
                    amountText.text.toString().toInt(),
                    addressText.text.toString()
                )
            }
        }

        return view
    }

    private fun showConfirmDialog(context: Context, amount: Int, address: String) {
        AlertDialog.Builder(context)
            .setTitle(getString(R.string.payment__dialog_confirm_title))
            .setMessage(
                getString(
                    R.string.payment__dialog_confirm_message,
                    amount,
                    address
                )
            )
            .setPositiveButton(getString(R.string.payment__dialog_confirm_button_positive)) { dialog, which ->
                progressBar.visibility = View.VISIBLE
                overlayView.visibility = View.VISIBLE
                val repository = PaymentRepositoryImpl()
                val res = repository.sendPayment(
                    addressText.text.toString(),
                    amountText.text.toString().toInt(),
                    "0x0000000000000000000000000000000000000001" // TODO: fix later
                )
                if (res != null) {
                    showCompletedDialog(context,
                        res.amount,
                        res.to
                    )
                }

            }
            .setNegativeButton(getString(R.string.payment__dialog_confirm_button_negative)) { dialog, which ->
                // do nothing
            }
            .show()
    }

    private fun showCompletedDialog(context: Context, amount: Int, address: String) {
        AlertDialog.Builder(context)
            .setTitle(getString(R.string.payment__dialog_completed_title))
            .setMessage(
                getString(
                    R.string.payment__dialog_completed_message,
                    amount,
                    address
                )
            )
            .setPositiveButton(getString(R.string.payment__dialog_completed_button_positive)) { dialog, which ->
                progressBar.visibility = View.GONE
                overlayView.visibility = View.GONE

                transition.finish()
            }
            .show()
    }
}
