package com.cryptoeconomicslab.demo_payment_usecase.ui.home.payment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cryptoeconomicslab.demo_payment_usecase.R
import com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity.PaymentHistory
import com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity.PaymentHistoryStatus
import com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity.PaymentHistoryType

class PaymentViewAdapter(private val context: Context, private val items: List<PaymentHistory>) :
    RecyclerView.Adapter<PaymentViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.item_cell_payment, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = this.items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            val paymentHistory: PaymentHistory = items[position]
            when (paymentHistory.historyType) {
                PaymentHistoryType.SEND -> {
                    typeIcon.setImageDrawable(context.getDrawable(R.drawable.ic_send))
                    typeIcon.background = context.getDrawable(R.drawable.image_send_background)
                }
                PaymentHistoryType.RECEIVE -> {
                    typeIcon.setImageDrawable(context.getDrawable(R.drawable.ic_recieve))
                    typeIcon.background = context.getDrawable(R.drawable.image_recieve_background)
                }
            }
            amountText.text = paymentHistory.amount.toString() + "ETH"

            when (paymentHistory.status) {
                PaymentHistoryStatus.CONFIRMED -> {
                    statusText.text = "confirmed"
                    statusText.background =
                        context.getDrawable(R.drawable.label_status_confirmed_background)
                }
                PaymentHistoryStatus.PENDING -> {
                    statusText.text = "pending"
                    statusText.background =
                        context.getDrawable(R.drawable.label_status_pending_background)
                }
                PaymentHistoryStatus.FAILED -> {
                    statusText.text = "failed"
                    statusText.background =
                        context.getDrawable(R.drawable.label_status_failed_background)
                }
            }

            addressText.text = paymentHistory.address
            timeStampText.text = paymentHistory.timestamp.toString()
        }
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val typeIcon: ImageView = view.findViewById(R.id.type_icon)
        val amountText: TextView = view.findViewById(R.id.amount_text)
        val statusText: TextView = view.findViewById(R.id.status_text)
        val addressText: TextView = view.findViewById(R.id.address_text)
        val timeStampText: TextView = view.findViewById(R.id.timestamp_text)
    }
}

