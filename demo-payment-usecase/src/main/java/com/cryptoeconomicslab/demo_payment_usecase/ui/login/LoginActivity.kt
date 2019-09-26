package com.cryptoeconomicslab.demo_payment_usecase.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import androidx.appcompat.app.AppCompatActivity
import com.cryptoeconomicslab.demo_payment_usecase.R
import com.cryptoeconomicslab.demo_payment_usecase.ui.home.HomeActivity
import android.os.StrictMode.setThreadPolicy
import com.cryptoeconomicslab.demo_payment_usecase.repository.wallet.WalletRepositoryImpl


class LoginActivity : AppCompatActivity(), Transition {

    companion object {
        fun createIntent(context: Context): Intent = Intent(context, LoginActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        setContentView(R.layout.activity_login)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, LoginFragment(this))
            .commit()

        val repository = WalletRepositoryImpl(this)
        if (repository.isLoggedIn()) {
            moveToHome()
        }
    }

    override fun moveToHome() {
        startActivity(HomeActivity.createIntent(this))
    }

    override fun finishScreen() {
        finish()
    }
}
