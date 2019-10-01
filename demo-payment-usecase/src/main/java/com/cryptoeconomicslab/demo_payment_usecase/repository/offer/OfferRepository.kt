package com.cryptoeconomicslab.demo_payment_usecase.repository.offer

import com.cryptoeconomicslab.plasma_android_sdk.httpClient.Address
import com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity.ExchangeHistory
import com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity.ExchangeOffer
import com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity.NewOffer

interface OfferRepository {
    fun getOffers(): List<ExchangeOffer>

    fun getOfferHistories(): List<ExchangeHistory>

    fun createOffer(tokenAddress: Address, amount: Int, counterTokenAddress: Address, counterAmount: Int): NewOffer?

    fun sendExchange(exchangeId: String)
}