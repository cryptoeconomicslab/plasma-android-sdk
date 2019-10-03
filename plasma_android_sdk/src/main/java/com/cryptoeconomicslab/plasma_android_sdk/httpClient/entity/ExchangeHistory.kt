package com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity

import java.util.*


/**
 * ExchangeHistory data class
 */
data class ExchangeHistory(
    val exchangeId: String,
    val historyType: ExchangeHistoryType,
    val tokenAddress: String,
    val amount: Int,
    val status: ExchangeHistoryStatus,
    val counterParty: CounterParty,
    val timestamp: Date
)

enum class ExchangeHistoryType {
    OFFER,
    OFFERED,
}

enum class ExchangeHistoryStatus {
    CONFIRMED,
    PENDING,
    FAILED,
}