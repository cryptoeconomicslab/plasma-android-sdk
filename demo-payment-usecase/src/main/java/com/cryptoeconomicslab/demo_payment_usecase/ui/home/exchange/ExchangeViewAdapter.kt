package com.cryptoeconomicslab.demo_payment_usecase.ui.home.exchange

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cryptoeconomicslab.demo_payment_usecase.R
import com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity.ExchangeOffer

class ExchangeViewAdapter(private val context: Context, private val items: List<ExchangeOffer>) :
    RecyclerView.Adapter<ExchangeViewAdapter.ViewHolder>() {

    var itemClickListener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.item_cell_exchange, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = this.items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            val exchangeOffer: ExchangeOffer = items[position]
            sourceAmountText.text = context.getString(
                R.string.offer__amount_text,
                exchangeOffer.amount,
                exchangeOffer.tokenId.toString()
            )
            targetAmountText.text = context.getString(
                R.string.offer__amount_text,
                exchangeOffer.counterParty.amount,
                exchangeOffer.counterParty.tokenId.toString()
            )
            addressText.text = exchangeOffer.counterParty.address

            view.setOnClickListener {
                itemClickListener?.onItemClick(items[position])
            }
        }
    }

    fun setOnItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val sourceAmountText: TextView = view.findViewById(R.id.source_amount_text)
        val targetAmountText: TextView = view.findViewById(R.id.target_amount_text)
        val addressText: TextView = view.findViewById(R.id.address_text)
    }

    interface ItemClickListener {
        fun onItemClick(item: ExchangeOffer)
    }
}