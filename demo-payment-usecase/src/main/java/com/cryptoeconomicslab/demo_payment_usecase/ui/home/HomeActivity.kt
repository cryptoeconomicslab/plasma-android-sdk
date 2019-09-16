package com.cryptoeconomicslab.demo_payment_usecase.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.cryptoeconomicslab.demo_payment_usecase.R
import com.cryptoeconomicslab.demo_payment_usecase.ui.home.exchange.ExchangeFragment
import com.cryptoeconomicslab.demo_payment_usecase.ui.home.payment.PaymentFragment
import com.cryptoeconomicslab.demo_payment_usecase.ui.home.wallet.WalletFragment
import com.cryptoeconomicslab.demo_payment_usecase.ui.new_offers.NewOfferActivity
import com.cryptoeconomicslab.demo_payment_usecase.ui.new_payment.NewPaymentActivity
import com.cryptoeconomicslab.demo_payment_usecase.ui.offer_history.OfferHistoryActivity
import com.cryptoeconomicslab.demo_payment_usecase.ui.scan_qr_code.ScanQRCodeActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity(), Transition, ActionBarCallback {

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
                            .replace(R.id.container, WalletFragment.getFragment(this@HomeActivity))
                            .commit()
                        true
                    }
                    R.id.menu_payment -> {
                        supportFragmentManager.beginTransaction()
                            .replace(
                                R.id.container,
                                PaymentFragment.getFragment(this@HomeActivity, this@HomeActivity)
                            )
                            .commit()
                        true
                    }
                    R.id.menu_exchange -> {
                        supportFragmentManager.beginTransaction()
                            .replace(
                                R.id.container,
                                ExchangeFragment.getFragment(this@HomeActivity, this@HomeActivity)
                            )
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        when (bottomNavigationView.selectedItemId) {
            R.id.menu_wallet -> {
                menuInflater.inflate(R.menu.menu_action_bar_wallet, menu)
            }
            R.id.menu_payment -> {
                // no menu to show
            }
            R.id.menu_exchange -> {
                menuInflater.inflate(R.menu.menu_action_bar_offer, menu)
            }
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_camera -> {
                moveToQRCodeScreen()
            }
            R.id.menu_history -> {
                moveToOfferHistoryScreen()
            }
        }
        return true
    }

    /**
     * see {@code Transition}
     */
    override fun moveToQRCodeScreen() {
        startActivity(ScanQRCodeActivity.createIntent(this))
    }

    override fun moveToNewPaymentScreen() {
        startActivity(NewPaymentActivity.createIntent(this))
    }

    override fun moveToNewOfferScreen() {
        startActivity(NewOfferActivity.createIntent(this))
    }

    override fun moveToOfferHistoryScreen() {
        startActivity(OfferHistoryActivity.createIntent(this))
    }

    /**
     * see {@code ActionBarCallback}
     */
    override fun setActionBarTitle(title: String) {
        setTitle(title)
    }

    override fun clearActionBarMenu() {
        invalidateOptionsMenu()
    }
}
