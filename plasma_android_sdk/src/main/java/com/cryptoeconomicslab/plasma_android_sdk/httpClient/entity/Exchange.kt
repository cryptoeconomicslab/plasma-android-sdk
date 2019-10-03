package com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity

import com.cryptoeconomicslab.plasma_android_sdk.httpClient.Address

/**
 * ExchangeOffer data class
 */
data class Exchange(
    val exchangeId: String,
    val from: Address
)