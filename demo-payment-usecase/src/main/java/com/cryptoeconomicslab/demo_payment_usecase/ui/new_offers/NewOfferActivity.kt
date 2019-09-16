package com.cryptoeconomicslab.demo_payment_usecase.ui.new_offers

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.cryptoeconomicslab.demo_payment_usecase.R

class NewOfferActivity : AppCompatActivity() {

    companion object {
        fun createIntent(context: Context): Intent = Intent(context, NewOfferActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_offer)

        setTitle(R.string.screen__new_offer_name)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return true
    }
}
