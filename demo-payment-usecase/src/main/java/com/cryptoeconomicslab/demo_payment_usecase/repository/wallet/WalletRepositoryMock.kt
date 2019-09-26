package com.cryptoeconomicslab.demo_payment_usecase.repository.wallet

import com.cryptoeconomicslab.plasma_android_sdk.httpClient.Address
import com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity.Account
import com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity.Balance

class WalletRepositoryMock : WalletRepository {
    override fun getWallets(): List<Balance> = listOf(
        Balance(tokenAddress = "0x1234", tokenName = "ETH", balance = 120),
        Balance(tokenAddress = "0x1235", tokenName = "DAI", balance = 120)
    )

    override fun getAddress(): Address? {
        return "0x123456789abcdef123456789"
    }

    override fun createAccount(): Account {
        return Account(address = "0x123456789abcdef123456789", session = "session key")
    }
}