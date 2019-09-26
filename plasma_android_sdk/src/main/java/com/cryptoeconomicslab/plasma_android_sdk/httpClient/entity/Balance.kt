package com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity

import com.cryptoeconomicslab.plasma_android_sdk.httpClient.Address

/**
 * Balance data class
 */
data class Balance(
    val tokenAddress: Address,
    val tokenName: String,
    val balance: Int
)