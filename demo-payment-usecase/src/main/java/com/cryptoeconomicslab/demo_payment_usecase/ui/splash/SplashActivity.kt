package com.cryptoeconomicslab.demo_payment_usecase.ui.splash

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.cryptoeconomicslab.demo_payment_usecase.R
import com.cryptoeconomicslab.demo_payment_usecase.ui.home.HomeActivity
import com.cryptoeconomicslab.demo_payment_usecase.ui.login.LoginActivity

class SplashActivity : AppCompatActivity() {

    private val hasToken = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            if (hasToken) {
                startActivity(HomeActivity.createIntent(this))
            } else {
                startActivity(LoginActivity.createIntent(this))
            }

            finish()
        }, 3000)
    }
}
