package com.cryptoeconomicslab.demo_payment_usecase.repository.payment

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

    override fun sendPayment(): Payment {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}