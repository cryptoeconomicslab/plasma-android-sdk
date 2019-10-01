package com.cryptoeconomicslab.demo_payment_usecase.repository.offer

import com.cryptoeconomicslab.plasma_android_sdk.httpClient.Address
import com.cryptoeconomicslab.demo_payment_usecase.repository.payment.PaymentRepositoryImpl
import com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity.ExchangeHistory
import com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity.ExchangeOffer
import com.cryptoeconomicslab.plasma_android_sdk.httpClient.ExchangeOfferRequest
import com.cryptoeconomicslab.plasma_android_sdk.httpClient.HttpClient
import com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity.CounterParty
import com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity.NewOffer

class OfferRepositoryImpl : OfferRepository {
    companion object {
        val client = HttpClient()
    }
    override fun getOfferHistories(): List<ExchangeHistory> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getOffers(): List<ExchangeOffer> {
        return OfferRepositoryImpl.client.getExchangeOffers().getOrDefault(listOf())
    }

    override fun createOffer(tokenAddress: Address, amount: Int, counterTokenAddress: Address, counterAmount: Int): NewOffer? {
        val result = OfferRepositoryImpl.client.createExchangeOffer(ExchangeOfferRequest(
            tokenAddress = tokenAddress,
            amount = amount,
            counterParty = CounterParty(
                tokenAddress = counterTokenAddress,
                amount = counterAmount,
                address = null
            )
        ))
        return result.getOrNull()
    }

    override fun sendExchange() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}