package com.cryptoeconomicslab.demo_payment_usecase.repository.wallet

import com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity.Balance

class WalletRepositoryMock : WalletRepository {
    override fun getWallets(): List<Balance> = listOf(
        Balance(tokenId = 0, balance = 120),
        Balance(tokenId = 0, balance = 120),
        Balance(tokenId = 0, balance = 120)
    )
}