package com.cryptoeconomicslab.demo_payment_usecase.repository.wallet

import com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity.Balance

interface WalletRepository {
    fun getWallets(): List<Balance>
}