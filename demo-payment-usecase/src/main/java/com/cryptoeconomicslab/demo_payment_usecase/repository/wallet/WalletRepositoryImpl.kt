package com.cryptoeconomicslab.demo_payment_usecase.repository.wallet

import android.content.Context
import android.util.Log
import com.cryptoeconomicslab.plasma_android_sdk.httpClient.Address
import com.cryptoeconomicslab.plasma_android_sdk.httpClient.HttpClient
import com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity.Account
import com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity.Balance

class WalletRepositoryImpl(private val context: Context) : WalletRepository {
    companion object {
        val client = HttpClient()
    }

    override fun isLoggedIn(): Boolean {
        val preferences = context.getSharedPreferences("client_data", Context.MODE_PRIVATE)
        val session = preferences.getString("session", null)
        val address = preferences.getString("address", null)
        Log.d("Login", "Logged in with (session, address): ($session, $address)")
        if (session.isNullOrEmpty() || address.isNullOrEmpty()) {
            return false
        } else {
            client.loadSession(session, address)
            return true
        }
    }

    override fun getWallets(): List<Balance> {
        return client.getBalance().getOrDefault(emptyList())
    }

    override fun getAddress(): Address? {
        return client.getAddress()
    }

    override fun createAccount(): Account? {
        val account = client.createAccount()
        if (account.isSuccess) {
            val a = account.getOrNull();
            a?.let {
                val preferences = context.getSharedPreferences("client_data", Context.MODE_PRIVATE)
                val editor = preferences.edit()
                editor.putString("session", it.session)
                editor.putString("address", it.address)
                editor.apply()
            }
            return a
        }

        return null
    }
}