package com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity

import com.cryptoeconomicslab.plasma_android_sdk.httpClient.Address

/**
 * CounterParty data class
 * used to express an exchange's counter party property.
 * Address is Null when counter party is not yet confirmed.
 */
data class CounterParty(
    val tokenAddress: Address,
    val amount: Int,
    val address: Address?
)