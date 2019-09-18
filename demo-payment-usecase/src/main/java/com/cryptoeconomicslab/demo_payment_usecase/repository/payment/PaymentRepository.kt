package com.cryptoeconomicslab.demo_payment_usecase.repository.payment

import com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity.PaymentHistory

interface PaymentRepository {
    fun getPaymentHistories(): List<PaymentHistory>
}