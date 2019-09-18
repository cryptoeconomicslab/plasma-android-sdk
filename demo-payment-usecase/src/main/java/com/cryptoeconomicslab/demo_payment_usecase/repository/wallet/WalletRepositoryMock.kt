package com.cryptoeconomicslab.demo_payment_usecase.repository.wallet

import com.cryptoeconomicslab.plasma_android_sdk.httpClient.Address
import com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity.Balance

class WalletRepositoryMock : WalletRepository {
    override fun getWallets(): List<Balance> = listOf(
        Balance(tokenId = 0, balance = 120),
        Balance(tokenId = 0, balance = 120),
        Balance(tokenId = 0, balance = 120)
    )

    override fun getAddress(): Address? {
        return "0x123456789abcdef123456789"
    }

    override fun createAccount(): String {
        return "session key"
    }
}