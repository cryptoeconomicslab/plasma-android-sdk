package com.cryptoeconomicslab.demo_payment_usecase.repository.wallet

import com.cryptoeconomicslab.plasma_android_sdk.httpClient.Address
import com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity.Balance

class WalletRepositoryImpl : WalletRepository {
    override fun getWallets(): List<Balance> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAddress(): Address? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createAccount(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}