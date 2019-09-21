package com.cryptoeconomicslab.demo_payment_usecase.ui.offer_history

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cryptoeconomicslab.demo_payment_usecase.R
import com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity.ExchangeHistory
import com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity.ExchangeHistoryStatus

class OfferViewAdapter(private val context: Context, private val items: List<ExchangeHistory>) :
    RecyclerView.Adapter<OfferViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.item_cell_exchange_history, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            val exchangeHistory: ExchangeHistory = items[position]
            when (exchangeHistory.status) {
                ExchangeHistoryStatus.CONFIRMED -> {
                    statusText.text = "confirmed"
                    statusText.background =
                        context.getDrawable(R.drawable.label_status_confirmed_background)
                }
                ExchangeHistoryStatus.PENDING -> {
                    statusText.text = "pending"
                    statusText.background =
                        context.getDrawable(R.drawable.label_status_pending_background)
                }
                ExchangeHistoryStatus.FAILED -> {
                    statusText.text = "failed"
                    statusText.background =
                        context.getDrawable(R.drawable.label_status_failed_background)
                }
            }
            sourceAmountText.text = context.getString(
                R.string.offer_history__amount_with_scale,
                exchangeHistory.amount,
                "ETH"
            )
            targetAmountText.text = context.getString(
                R.string.offer_history__amount_with_scale,
                exchangeHistory.counterParty.amount,
                "DAI"
            )
            addressText.text = exchangeHistory.counterParty.address!!
        }
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val statusText: TextView = view.findViewById(R.id.status_text)
        val sourceAmountText: TextView = view.findViewById(R.id.source_amount_text)
        val targetAmountText: TextView = view.findViewById(R.id.target_amount_text)
        val addressText: TextView = view.findViewById(R.id.address_text)
    }
}