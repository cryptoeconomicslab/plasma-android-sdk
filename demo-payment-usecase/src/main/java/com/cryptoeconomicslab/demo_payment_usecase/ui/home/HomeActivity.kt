package com.cryptoeconomicslab.demo_payment_usecase.ui.home

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cryptoeconomicslab.demo_payment_usecase.R
import com.cryptoeconomicslab.demo_payment_usecase.repository.wallet.WalletRepositoryImpl
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

        const val QRCODE_REQUEST_CODE = 1
    }

    lateinit var bottomNavigationView: BottomNavigationView
    lateinit var addressText: TextView
    lateinit var copyAddressButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val repository = WalletRepositoryImpl(this)
        addressText = findViewById<TextView>(R.id.address_text).apply {
            this.text = repository.getAddress()
        }

        copyAddressButton = findViewById<Button>(R.id.copy_clipboard_button).apply {
            this.setOnClickListener {
                val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("wallet address", repository.getAddress())
                clipboard.setPrimaryClip(clip)

            }
        }

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
        startActivityForResult(ScanQRCodeActivity.createIntent(this), QRCODE_REQUEST_CODE)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (resultCode) {
            RESULT_OK -> {
                data?.let { intent ->
                    intent.extras?.let { bundle ->
                        // FIXME: add address to local
                        Toast.makeText(
                            this,
                            bundle.getString(ScanQRCodeActivity.KEY_RESULT_QRCODE),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
            RESULT_CANCELED -> {
                // do nothing
            }
            else -> {
                // do nothing
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
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
