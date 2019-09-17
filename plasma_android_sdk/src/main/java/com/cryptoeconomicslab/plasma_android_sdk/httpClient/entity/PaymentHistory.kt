package com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity

import com.cryptoeconomicslab.plasma_android_sdk.httpClient.Address
import java.util.*

/**
 * PaymentHistory data class
 */
data class PaymentHistory(
    val historyType: PaymentHistoryType,
    val amount: Int,
    val address: Address,
    val timestamp: Date,
    val status: PaymentHistoryStatus
)

enum class PaymentHistoryType {
    SEND,
    RECEIVE,
}

enum class PaymentHistoryStatus {
    CONFIRMED,
    PENDING,
    FAILED,
}