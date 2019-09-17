package com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity

import com.cryptoeconomicslab.plasma_android_sdk.httpClient.Address

/**
 * ExchangeOffer data class
 */
data class NewOffer(
    val offer: ExchangeOffer,
    val from: Address
)