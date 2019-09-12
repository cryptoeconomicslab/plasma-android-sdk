package com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity

import com.cryptoeconomicslab.plasma_android_sdk.httpClient.Address
import java.time.LocalDateTime

data class PaymentHistory(
    val type: String, // 'SEND' | 'RECEIVE'
    val amount: Int,
    val address: Address,
    val timestamp: LocalDateTime,
    val status: String // 'CONFIRMED' | 'STATUS' | 'FAILED'
)