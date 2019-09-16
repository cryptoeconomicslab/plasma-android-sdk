package com.cryptoeconomicslab.demo_payment_usecase.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cryptoeconomicslab.demo_payment_usecase.R
import com.cryptoeconomicslab.demo_payment_usecase.ui.home.HomeActivity

class LoginActivity : AppCompatActivity(), Transition {

    companion object {
        fun createIntent(context: Context): Intent = Intent(context, LoginActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, LoginFragment(this))
            .commit()
    }

    override fun moveToHome() {
        startActivity(HomeActivity.createIntent(this))
    }

    override fun finishScreen() {
        finish()
    }
}
