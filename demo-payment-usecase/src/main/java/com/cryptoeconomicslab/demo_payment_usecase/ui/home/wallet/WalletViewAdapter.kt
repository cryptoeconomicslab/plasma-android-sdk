package com.cryptoeconomicslab.demo_payment_usecase.ui.home.wallet

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cryptoeconomicslab.demo_payment_usecase.R
import com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity.Balance

class WalletViewAdapter(private val context: Context, private val items: List<Balance>) :
    RecyclerView.Adapter<WalletViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.item_cell_wallet, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = this.items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            amountText.text = items[position].balance.toString()
            tokenText.text = items[position].tokenId.toString()
            addressText.text = "0x123456789abcde123456789abcdef"
        }
    }


    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val amountText: TextView = view.findViewById(R.id.amount_text)
        val tokenText: TextView = view.findViewById(R.id.token_text)
        val addressText: TextView = view.findViewById(R.id.address_text)
    }
}