package com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity

import com.cryptoeconomicslab.plasma_android_sdk.httpClient.Address

/**
 * ExchangeOffer data class
 */
data class Payment(
    val from: Address,
    val to: Address,
    val amount: Int,
    val tokenId: Int
)