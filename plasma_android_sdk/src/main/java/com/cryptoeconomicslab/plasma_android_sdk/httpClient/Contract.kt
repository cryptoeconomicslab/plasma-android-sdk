package com.cryptoeconomicslab.plasma_android_sdk.httpClient

import com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity.*

internal interface HttpClientContract {
    // General
    fun getBalance(address: Address): Result<Balance>
    fun createAccount(password: String): Result<Account>

    // Payment
    fun getPaymentHistory(address: Address): Result<List<PaymentHistory>>

    // status: 201, error: 500
    fun sendPayment(from: Address, amount: Int, tokenId: String, to: Address): Result<Unit>

    // Exchange
    fun getExchangeOffers(): Result<List<ExchangeOffer>>
    fun getExchangeHistory(address: Address): Result<List<ExchangeHistory>>

    // status: 201, error: 500
    fun sendExchange(from: Address, exchangeId: Int): Result<Unit>

    // status: 201, error: 500
    fun createNewExchangeOffer(from: Address, offer: ExchangeOffer): Result<Unit>
}