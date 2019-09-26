package com.cryptoeconomicslab.demo_payment_usecase.repository.payment

import com.cryptoeconomicslab.plasma_android_sdk.httpClient.Address
import com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity.PaymentHistory
import com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity.Payment

interface PaymentRepository {
    fun getPaymentHistories(): List<PaymentHistory>

    fun sendPayment(to: Address, amount: Int, tokenAddress: Address): Payment?
}