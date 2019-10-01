package com.cryptoeconomicslab.plasma_android_sdk.httpClient

import com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity.*

internal interface HttpClientContract {
    // General
    fun getBalance(address: Address): Result<List<Balance>>
    fun createAccount(): Result<Account>

    // Payment
    fun getPaymentHistory(address: Address): Result<List<PaymentHistory>>

    // status: 201, error: 500
    fun sendPayment(from: Address, amount: Int, tokenId: Int, to: Address): Result<Payment>

    // Exchange
    fun getExchangeOffers(): Result<List<ExchangeOffer>>
    fun getExchangeHistory(address: Address): Result<List<ExchangeHistory>>

    // status: 201, error: 500
    fun sendExchange(from: Address, exchangeId: String): Result<Exchange>

    // status: 201, error: 500
    fun createExchangeOffer(from: Address, offer: ExchangeOffer): Result<NewOffer>
}