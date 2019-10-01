package com.cryptoeconomicslab.demo_payment_usecase.repository.offer

import com.cryptoeconomicslab.plasma_android_sdk.httpClient.Address
import com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity.*
import java.util.*

class OfferRepositoryMock : OfferRepository {
    override fun getOffers(): List<ExchangeOffer> = listOf(
        ExchangeOffer(
            exchangeId = "a",
            tokenAddress = "0x0000000000000000000000000000000000000000",
            amount = 12,
            counterParty = CounterParty(
                tokenAddress = "0x0000000000000000000000000000000000000001",
                amount = 120,
                address = "0x123456789abcdef123456789abcdef"
            )
        ),
        ExchangeOffer(
            exchangeId = "b",
            tokenAddress = "0x0000000000000000000000000000000000000000",
            amount = 12,
            counterParty = CounterParty(
                tokenAddress = "0x0000000000000000000000000000000000000001",
                amount = 120,
                address = "0x123456789abcdef123456789abcdef"
            )
        ),
        ExchangeOffer(
            exchangeId = "c",
            tokenAddress = "0x0000000000000000000000000000000000000000",
            amount = 12,
            counterParty = CounterParty(
                tokenAddress = "0x0000000000000000000000000000000000000001",
                amount = 120,
                address = "0x123456789abcdef123456789abcdef"
            )
        )
    )

    override fun getOfferHistories(): List<ExchangeHistory> = listOf(
        ExchangeHistory(
            exchangeId = "a",
            historyType = ExchangeHistoryType.OFFERED,
            tokenId = 0,
            amount = 12,
            status = ExchangeHistoryStatus.CONFIRMED,
            counterParty = CounterParty(
                tokenAddress = "0x0000000000000000000000000000000000000001",
                amount = 120,
                address = "0x123456789abcdef123456789abcdef"
            ),
            timestamp = Date()
        ),
        ExchangeHistory(
            exchangeId = "b",
            historyType = ExchangeHistoryType.OFFERED,
            tokenId = 0,
            amount = 12,
            status = ExchangeHistoryStatus.CONFIRMED,
            counterParty = CounterParty(
                tokenAddress = "0x0000000000000000000000000000000000000001",
                amount = 120,
                address = "0x123456789abcdef123456789abcdef"
            ),
            timestamp = Date()
        ),
        ExchangeHistory(
            exchangeId = "c",
            historyType = ExchangeHistoryType.OFFERED,
            tokenId = 0,
            amount = 12,
            status = ExchangeHistoryStatus.CONFIRMED,
            counterParty = CounterParty(
                tokenAddress = "0x0000000000000000000000000000000000000001",
                amount = 120,
                address = "0x123456789abcdef123456789abcdef"
            ),
            timestamp = Date()
        ),
        ExchangeHistory(
            exchangeId = "d",
            historyType = ExchangeHistoryType.OFFERED,
            tokenId = 0,
            amount = 12,
            status = ExchangeHistoryStatus.CONFIRMED,
            counterParty = CounterParty(
                tokenAddress = "0x0000000000000000000000000000000000000001",
                amount = 120,
                address = "0x123456789abcdef123456789abcdef"
            ),
            timestamp = Date()
        ),
        ExchangeHistory(
            exchangeId = "e",
            historyType = ExchangeHistoryType.OFFERED,
            tokenId = 0,
            amount = 12,
            status = ExchangeHistoryStatus.CONFIRMED,
            counterParty = CounterParty(
                tokenAddress = "0x0000000000000000000000000000000000000001",
                amount = 120,
                address = "0x123456789abcdef123456789abcdef"
            ),
            timestamp = Date()
        ),
        ExchangeHistory(
            exchangeId = "f",
            historyType = ExchangeHistoryType.OFFERED,
            tokenId = 0,
            amount = 12,
            status = ExchangeHistoryStatus.CONFIRMED,
            counterParty = CounterParty(
                tokenAddress = "0x0000000000000000000000000000000000000001",
                amount = 120,
                address = "0x123456789abcdef123456789abcdef"
            ),
            timestamp = Date()
        )
    )

    override fun createOffer(tokenAddress: Address, amount: Int, counterTokenAddress: Address, counterAmount: Int): NewOffer? {
        return null
    }

    override fun sendExchange(exchangeId: String) {
    }
}