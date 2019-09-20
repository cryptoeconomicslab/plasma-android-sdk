package com.cryptoeconomicslab.demo_payment_usecase.ui.home.exchange


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cryptoeconomicslab.demo_payment_usecase.R
import com.cryptoeconomicslab.demo_payment_usecase.repository.offer.OfferRepository
import com.cryptoeconomicslab.demo_payment_usecase.repository.offer.OfferRepositoryMock
import com.cryptoeconomicslab.demo_payment_usecase.ui.home.ActionBarCallback
import com.cryptoeconomicslab.demo_payment_usecase.ui.home.Transition
import com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity.ExchangeOffer

/**
 * A simple [Fragment] subclass.
 */
class ExchangeFragment(
    private val actionBarCallback: ActionBarCallback,
    private val transition: Transition
) : Fragment() {

    lateinit var progressBar: ProgressBar
    lateinit var overlayView: View

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

        val repository: OfferRepository = OfferRepositoryMock()

        val view = inflater.inflate(R.layout.fragment_exchange, container, false)

        progressBar = view.findViewById(R.id.progress_bar)
        overlayView = view.findViewById(R.id.overlay)

        view.findViewById<Button>(R.id.add_button).apply {
            setOnClickListener {
                transition.moveToNewOfferScreen()
            }
        }

        view.findViewById<RecyclerView>(R.id.recycler_view).apply {
            adapter = ExchangeViewAdapter(context, repository.getOffers()).apply {
                setOnItemClickListener(object : ExchangeViewAdapter.ItemClickListener {
                    override fun onItemClick(item: ExchangeOffer) {
                        showConfirmDialog(
                            context,
                            item.amount,
                            item.counterParty.amount,
                            item.counterParty.address!!
                        )
                    }
                })
            }

            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        overlayView.setOnClickListener {
            context?.let {
                showCompletedDialog(it)
            }
        }
        return view
    }

    private fun showConfirmDialog(
        context: Context,
        sourceAmount: Int,
        targetAmount: Int,
        address: String
    ) {
        AlertDialog.Builder(context)
            .setTitle(getString(R.string.exchange__dialog_confirm_title))
            .setMessage(
                getString(
                    R.string.exchange__dialog_confirm_message,
                    sourceAmount,
                    "ETH",
                    targetAmount,
                    "DAI",
                    address
                )
            )
            .setPositiveButton(getString(R.string.exchange__dialog_confirm_button_positive)) { dialog, which ->
                progressBar.visibility = View.VISIBLE
                overlayView.visibility = View.VISIBLE
            }
            .setNegativeButton(getString(R.string.exchange__dialog_confirm_button_negative)) { dialog, which ->
                // do nothing
            }
            .show()
    }

    private fun showCompletedDialog(context: Context) {
        AlertDialog.Builder(context)
            .setMessage(
                getString(R.string.exchange__dialog_completed_message)
            )
            .setPositiveButton(getString(R.string.exchange__dialog_completed_button_positive)) { dialog, which ->
                progressBar.visibility = View.GONE
                overlayView.visibility = View.GONE
            }
            .show()
    }
}
