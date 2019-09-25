package com.cryptoeconomicslab.demo_payment_usecase.repository.payment

import com.cryptoeconomicslab.plasma_android_sdk.httpClient.Address
import com.cryptoeconomicslab.plasma_android_sdk.httpClient.HttpClient
import com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity.Payment
import com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity.PaymentHistory

class PaymentRepositoryImpl() : PaymentRepository {
    companion object {
        val client = HttpClient()
    }

    override fun getPaymentHistories(): List<PaymentHistory> {
        return client.getPaymentHistory().getOrDefault(emptyList())
    }

    override fun sendPayment(to: Address, amount: Int, tokenAddress: Address): Payment? {
        val result = client.sendPayment(to, amount, tokenAddress)
        if (result.isSuccess) {
            return result.getOrNull()!!
        }

        return null
    }
}