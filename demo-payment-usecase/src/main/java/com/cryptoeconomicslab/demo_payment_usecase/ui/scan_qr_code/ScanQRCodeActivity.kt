package com.cryptoeconomicslab.demo_payment_usecase.ui.scan_qr_code

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.cryptoeconomicslab.demo_payment_usecase.R

class ScanQRCodeActivity : AppCompatActivity(), Transition {

    companion object {
        fun createIntent(context: Context): Intent = Intent(context, ScanQRCodeActivity::class.java)

        const val KEY_RESULT_QRCODE = "result_qrcode"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_qrcode)

        setTitle(R.string.screen__qr_name)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, ScanQRCodeFragment.getFragment(this))
            .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                setResult(RESULT_CANCELED, intent)
                finish()
            }
        }
        return true
    }

    override fun finishWithResult(text: String) {
        intent.putExtra(KEY_RESULT_QRCODE, text)
        setResult(RESULT_OK, intent)
        finish()
    }
}
