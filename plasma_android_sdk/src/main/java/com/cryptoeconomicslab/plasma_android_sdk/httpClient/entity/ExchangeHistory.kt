package com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity

import java.time.LocalDateTime


/**
 * ExchangeHistory data class
 */
data class ExchangeHistory(
    val exchangeId: String,
    val type: String, // 'OFFERED' | 'OFFERED'
    val tokenId: String,
    val amount: Int,
    val status: String, // 'CONFIRMED' | 'PENDING'
    val counterParty: CounterParty,
    val timestamp: LocalDateTime
)