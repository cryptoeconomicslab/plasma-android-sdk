package com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity

import com.cryptoeconomicslab.plasma_android_sdk.httpClient.Address


/**
 * ExchangeOffer data class
 */
data class ExchangeOffer(
    val exchangeId: String,
    val tokenAddress: Address,
    val amount: Int,
    val counterParty: CounterParty
)