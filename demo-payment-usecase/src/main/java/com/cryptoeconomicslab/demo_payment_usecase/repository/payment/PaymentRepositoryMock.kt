package com.cryptoeconomicslab.demo_payment_usecase.repository.payment

import com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity.PaymentHistory
import com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity.PaymentHistoryStatus
import com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity.PaymentHistoryType
import java.util.*

class PaymentRepositoryMock : PaymentRepository {
    override fun getPaymentHistories(): List<PaymentHistory> = listOf(
        PaymentHistory(
            PaymentHistoryType.RECEIVE,
            amount = 120,
            address = "0x123456789abcdef123456789",
            timestamp = Date(),
            status = PaymentHistoryStatus.PENDING
        ),
        PaymentHistory(
            PaymentHistoryType.SEND,
            amount = 120,
            address = "0x123456789abcdef123456789",
            timestamp = Date(),
            status = PaymentHistoryStatus.CONFIRMED
        ),
        PaymentHistory(
            PaymentHistoryType.RECEIVE,
            amount = 120,
            address = "0x123456789abcdef123456789",
            timestamp = Date(),
            status = PaymentHistoryStatus.FAILED
        ),
        PaymentHistory(
            PaymentHistoryType.SEND,
            amount = 120,
            address = "0x123456789abcdef123456789",
            timestamp = Date(),
            status = PaymentHistoryStatus.PENDING
        ),
        PaymentHistory(
            PaymentHistoryType.RECEIVE,
            amount = 120,
            address = "0x123456789abcdef123456789",
            timestamp = Date(),
            status = PaymentHistoryStatus.CONFIRMED
        ),
        PaymentHistory(
            PaymentHistoryType.RECEIVE,
            amount = 120,
            address = "0x123456789abcdef123456789",
            timestamp = Date(),
            status = PaymentHistoryStatus.CONFIRMED
        ),
        PaymentHistory(
            PaymentHistoryType.RECEIVE,
            amount = 120,
            address = "0x123456789abcdef123456789",
            timestamp = Date(),
            status = PaymentHistoryStatus.CONFIRMED
        ),
        PaymentHistory(
            PaymentHistoryType.RECEIVE,
            amount = 120,
            address = "0x123456789abcdef123456789",
            timestamp = Date(),
            status = PaymentHistoryStatus.CONFIRMED
        ),
        PaymentHistory(
            PaymentHistoryType.RECEIVE,
            amount = 120,
            address = "0x123456789abcdef123456789",
            timestamp = Date(),
            status = PaymentHistoryStatus.CONFIRMED
        )
    )
}