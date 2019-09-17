package com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity


/**
 * ExchangeOffer data class
 */
data class ExchangeOffer(
    val exchangeId: Int,
    val tokenId: Int,
    val amount: Int,
    val counterParty: CounterParty
)