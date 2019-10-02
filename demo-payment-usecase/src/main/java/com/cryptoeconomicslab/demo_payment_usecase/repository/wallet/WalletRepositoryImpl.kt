package com.cryptoeconomicslab.demo_payment_usecase.repository.wallet

import android.content.Context
import android.util.Log
import com.cryptoeconomicslab.plasma_android_sdk.httpClient.Address
import com.cryptoeconomicslab.plasma_android_sdk.httpClient.HttpClient
import com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity.Account
import com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity.Balance
import com.cryptoeconomicslab.plasma_android_sdk.PlasmaClient

class WalletRepositoryImpl(private val context: Context) : WalletRepository {
    companion object {
        val client = HttpClient()
        val native_client = PlasmaClient()
    }

    override fun isLoggedIn(): Boolean {
        val preferences = context.getSharedPreferences("client_data", Context.MODE_PRIVATE)
        val session = preferences.getString("session", null)
        val address = preferences.getString("address", null)
        Log.d("Login", "Logged in with (session, address): ($session, $address)")
        if (session.isNullOrEmpty() || address.isNullOrEmpty()) {
            return false
        } else {
            native_client.loadSession(session)
            return true
        }
    }



    override fun getWallets(): List<Balance> {
        return listOf(Balance(
            "0x00000",
            "ETH",
            native_client.get_balance()
        ))
        //return client.getBalance().getOrDefault(emptyList())
    }

    override fun getAddress(): Address? {
        return client.getAddress()
    }

    override fun createAccount(): Account? {
        val session = native_client.createAccount()
        val preferences = context.getSharedPreferences("client_data", Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putString("session", session)
//        editor.putString("address", it.address)
        return Account("0x000000", session)
    }
}