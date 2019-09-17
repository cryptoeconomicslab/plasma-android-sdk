package com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity

import java.time.LocalDateTime


/**
 * ExchangeHistory data class
 */
data class ExchangeHistory(
    val exchangeId: Int,
    val historyType: ExchangeHistoryType,
    val tokenId: Int,
    val amount: Int,
    val status: ExchangeHistoryStatus,
    val counterParty: CounterParty,
    val timestamp: LocalDateTime
)

enum class ExchangeHistoryType {
    OFFERED,
}

enum class ExchangeHistoryStatus {
    CONFIRMED,
    PENDING,
}