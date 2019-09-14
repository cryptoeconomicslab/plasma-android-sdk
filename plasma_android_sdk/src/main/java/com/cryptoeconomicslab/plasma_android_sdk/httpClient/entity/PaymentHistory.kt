package com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity

import com.cryptoeconomicslab.plasma_android_sdk.httpClient.Address
import java.time.LocalDateTime

/**
 * PaymentHistory data class
 */
data class PaymentHistory(
    val type: PaymentHistoryType,
    val amount: Int,
    val address: Address,
    val timestamp: LocalDateTime,
    val status: PaymentHistoryStatus
)

enum class PaymentHistoryType {
    SEND,
    RECEIVE,
}

enum class PaymentHistoryStatus {
    CONFIRMED,
    STATUS,
    FAILED,
}