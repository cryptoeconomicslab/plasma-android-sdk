package com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity

import com.cryptoeconomicslab.plasma_android_sdk.httpClient.Address

/**
 * Account data class
 */
data class Account(
    val address: Address,
    val session: String
)