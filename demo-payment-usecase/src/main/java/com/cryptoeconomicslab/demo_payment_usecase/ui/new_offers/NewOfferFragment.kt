package com.cryptoeconomicslab.demo_payment_usecase.ui.new_offers


import android.content.Context
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.cryptoeconomicslab.demo_payment_usecase.R

/**
 * A simple [Fragment] subclass.
 */
class NewOfferFragment(val transition: Transition) : Fragment() {

    lateinit var progressBar: ProgressBar
    lateinit var overlayView: View

    lateinit var sourceText: EditText
    lateinit var sourceSpinner: Spinner

    lateinit var targetText: EditText
    lateinit var targetSpinner: Spinner

    companion object {
        fun getFragment(transition: Transition) = NewOfferFragment(transition)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_new_offer, container, false)

        progressBar = view.findViewById(R.id.progress_bar)
        overlayView = view.findViewById(R.id.overlay)

        sourceText = view.findViewById(R.id.source_amount_text)
        sourceSpinner = view.findViewById(R.id.source_scale_spinner)

        targetText = view.findViewById(R.id.target_amount_text)
        targetSpinner = view.findViewById(R.id.target_scale_spinner)

        view.findViewById<Spinner>(R.id.target_scale_spinner).apply {
            val adapter = ArrayAdapter(
                context,
                R.layout.spinner_cell,
                resources.getStringArray(R.array.amount_scale_values)
            )
            adapter.setDropDownViewResource(R.layout.spinner_drop_down_cell)
            setAdapter(adapter)

            background.setColorFilter(context.getColor(R.color.colorLight), PorterDuff.Mode.SRC_ATOP);
        }

        view.findViewById<Spinner>(R.id.source_scale_spinner).apply {
            val adapter = ArrayAdapter(
                context,
                R.layout.spinner_cell,
                resources.getStringArray(R.array.amount_scale_values)
            )
            adapter.setDropDownViewResource(R.layout.spinner_drop_down_cell)
            setAdapter(adapter)

            background.setColorFilter(context.getColor(R.color.colorLight), PorterDuff.Mode.SRC_ATOP);
        }

        view.findViewById<Button>(R.id.offer_button).apply {
            setOnClickListener {
                progressBar.visibility = View.VISIBLE
                overlayView.visibility = View.VISIBLE
            }
        }

        // TODO: change trigger
        overlayView.setOnClickListener {
            context?.let {
                val targetAmount = targetText.text.toString().toFloat()
                val targetScale = targetSpinner.selectedItem.toString()
                val sourceAmount = sourceText.text.toString().toFloat()
                val sourceScale = sourceSpinner.selectedItem.toString()
                showCompletedDialog(it, sourceAmount, sourceScale, targetAmount, targetScale)
            }
        }

        return view
    }

    private fun showCompletedDialog(context: Context, sourceAmount: Float, sourceScale:String, targetAmount: Float, targetScale: String) {
        AlertDialog.Builder(context)
            .setTitle(getString(R.string.new_offer__dialog_completed_title))
            .setMessage(
                getString(
                    R.string.new_offer__dialog_completed_message,
                    sourceAmount,
                    sourceScale,
                    targetAmount,
                    targetScale
                )
            )
            .setPositiveButton(getString(R.string.new_offer__dialog_completed_button_positive)) { dialog, which ->
                progressBar.visibility = View.GONE
                overlayView.visibility = View.GONE

                transition.finish()
            }
            .show()
    }

}
