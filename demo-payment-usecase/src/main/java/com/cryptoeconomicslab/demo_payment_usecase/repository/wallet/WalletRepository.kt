package com.cryptoeconomicslab.demo_payment_usecase.repository.wallet

import com.cryptoeconomicslab.plasma_android_sdk.httpClient.Address
import com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity.Account
import com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity.Balance

interface WalletRepository {
    fun isLoggedIn(): Boolean {
        return false
    }

    fun getWallets(): List<Balance>

    fun getAddress(): Address?

    // return session_key
    fun createAccount(): Account?
}