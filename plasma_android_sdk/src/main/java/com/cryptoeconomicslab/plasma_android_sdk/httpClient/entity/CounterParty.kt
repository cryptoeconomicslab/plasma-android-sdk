package com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity

import com.cryptoeconomicslab.plasma_android_sdk.httpClient.Address

data class CounterParty(
    val tokenId: String,
    val amount: Int,
    val address: Address?
)