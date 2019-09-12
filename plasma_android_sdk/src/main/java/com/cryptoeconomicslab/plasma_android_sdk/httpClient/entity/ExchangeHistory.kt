package com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity

import java.time.LocalDateTime


data class ExchangeHistory(
    val type: String, // 'OFFERED' | 'OFFERED'
    val tokenId: String,
    val amount: Int,
    val status: String, // 'CONFIRMED' | 'PENDING'
    val counterParty: CounterParty,
    val timestamp: LocalDateTime
)