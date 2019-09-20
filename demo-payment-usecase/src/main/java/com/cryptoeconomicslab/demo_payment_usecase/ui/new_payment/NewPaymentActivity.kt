package com.cryptoeconomicslab.demo_payment_usecase.ui.new_payment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.cryptoeconomicslab.demo_payment_usecase.R

class NewPaymentActivity : AppCompatActivity(), Transition {

    companion object {
        fun createIntent(context: Context): Intent = Intent(context, NewPaymentActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_payment)

        setTitle(R.string.screen__new_payment_name)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, NewPaymentFragment.getFragment(this))
            .commit()
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
