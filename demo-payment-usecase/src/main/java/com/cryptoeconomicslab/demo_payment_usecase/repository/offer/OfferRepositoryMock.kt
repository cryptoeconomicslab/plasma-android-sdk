package com.cryptoeconomicslab.demo_payment_usecase.repository.offer

import com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity.*
import java.util.*

class OfferRepositoryMock : OfferRepository {
    override fun getOffers(): List<ExchangeOffer> = listOf(
        ExchangeOffer(
            exchangeId = 0,
            tokenId = 0,
            amount = 12,
            counterParty = CounterParty(
                tokenId = 1,
                amount = 120,
                address = "0x123456789abcdef123456789abcdef"
            )
        ),
        ExchangeOffer(
            exchangeId = 0,
            tokenId = 0,
            amount = 12,
            counterParty = CounterParty(
                tokenId = 1,
                amount = 120,
                address = "0x123456789abcdef123456789abcdef"
            )
        ),
        ExchangeOffer(
            exchangeId = 0,
            tokenId = 0,
            amount = 12,
            counterParty = CounterParty(
                tokenId = 1,
                amount = 120,
                address = "0x123456789abcdef123456789abcdef"
            )
        )
    )

    override fun getOfferHistories(): List<ExchangeHistory> = listOf(
        ExchangeHistory(
            exchangeId = 0,
            historyType = ExchangeHistoryType.OFFERED,
            tokenId = 0,
            amount = 12,
            status = ExchangeHistoryStatus.CONFIRMED,
            counterParty = CounterParty(
                tokenId = 1,
                amount = 120,
                address = "0x123456789abcdef123456789abcdef"
            ),
            timestamp = Date()
        ),
        ExchangeHistory(
            exchangeId = 0,
            historyType = ExchangeHistoryType.OFFERED,
            tokenId = 0,
            amount = 12,
            status = ExchangeHistoryStatus.CONFIRMED,
            counterParty = CounterParty(
                tokenId = 1,
                amount = 120,
                address = "0x123456789abcdef123456789abcdef"
            ),
            timestamp = Date()
        ),
        ExchangeHistory(
            exchangeId = 0,
            historyType = ExchangeHistoryType.OFFERED,
            tokenId = 0,
            amount = 12,
            status = ExchangeHistoryStatus.CONFIRMED,
            counterParty = CounterParty(
                tokenId = 1,
                amount = 120,
                address = "0x123456789abcdef123456789abcdef"
            ),
            timestamp = Date()
        ),
        ExchangeHistory(
            exchangeId = 0,
            historyType = ExchangeHistoryType.OFFERED,
            tokenId = 0,
            amount = 12,
            status = ExchangeHistoryStatus.CONFIRMED,
            counterParty = CounterParty(
                tokenId = 1,
                amount = 120,
                address = "0x123456789abcdef123456789abcdef"
            ),
            timestamp = Date()
        ),
        ExchangeHistory(
            exchangeId = 0,
            historyType = ExchangeHistoryType.OFFERED,
            tokenId = 0,
            amount = 12,
            status = ExchangeHistoryStatus.CONFIRMED,
            counterParty = CounterParty(
                tokenId = 1,
                amount = 120,
                address = "0x123456789abcdef123456789abcdef"
            ),
            timestamp = Date()
        ),
        ExchangeHistory(
            exchangeId = 0,
            historyType = ExchangeHistoryType.OFFERED,
            tokenId = 0,
            amount = 12,
            status = ExchangeHistoryStatus.CONFIRMED,
            counterParty = CounterParty(
                tokenId = 1,
                amount = 120,
                address = "0x123456789abcdef123456789abcdef"
            ),
            timestamp = Date()
        )
    )

    override fun createOffer() {
    }

    override fun sendExchange() {
    }
}