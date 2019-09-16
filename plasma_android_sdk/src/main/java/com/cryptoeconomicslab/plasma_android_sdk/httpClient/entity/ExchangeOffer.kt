package com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity


/**
 * ExchangeOffer data class
 */
data class ExchangeOffer(
    val exchangeId: String,
    val type: String,
    val tokenId: String,
    val amount: Int,
    val counterParty: CounterParty
)