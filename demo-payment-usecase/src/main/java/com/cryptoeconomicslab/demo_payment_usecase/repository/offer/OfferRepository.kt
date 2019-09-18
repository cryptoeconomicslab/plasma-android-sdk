package com.cryptoeconomicslab.demo_payment_usecase.repository.offer

import com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity.ExchangeHistory
import com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity.ExchangeOffer

interface OfferRepository {
    fun getOffers(): List<ExchangeOffer>

    fun getOfferHistories(): List<ExchangeHistory>
}