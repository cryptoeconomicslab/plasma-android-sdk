package com.cryptoeconomicslab.demo_payment_usecase.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cryptoeconomicslab.demo_payment_usecase.R
import com.cryptoeconomicslab.demo_payment_usecase.ui.home.exchange.ExchangeFragment
import com.cryptoeconomicslab.demo_payment_usecase.ui.home.payment.PaymentFragment
import com.cryptoeconomicslab.demo_payment_usecase.ui.home.wallet.WalletFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    companion object {
        fun createIntent(context: Context): Intent = Intent(context, HomeActivity::class.java)
    }

    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation).apply {
            setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.menu_wallet -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.container, WalletFragment.getFragment())
                            .commit()
                        true
                    }
                    R.id.menu_payment -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.container, PaymentFragment.getFragment())
                            .commit()
                        true
                    }
                    R.id.menu_exchange -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.container, ExchangeFragment.getFragment())
                            .commit()
                        true
                    }
                    else -> {
                        false
                    }
                }
            }
            selectedItemId = R.id.menu_wallet
        }
    }
}
