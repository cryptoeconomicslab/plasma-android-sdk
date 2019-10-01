package com.cryptoeconomicslab.plasma_android_sdk.httpClient

import com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity.*
import retrofit2.Call
import retrofit2.http.*

typealias Address = String

/**
 * SendPaymentRequestBody data class
 */
data class SendPaymentRequestBody(
    val session: String,
    val from: Address,
    val to: Address,
    val amount: Int,
    val tokenAddress: Address
)

data class ExchangeOfferRequest(
    val tokenAddress: Address,
    val amount: Int,
    val counterParty: CounterParty
)

data class CreateExchangeOfferRequest(
    val from: Address,
    val offer: ExchangeOfferRequest,
    val session: String
)

data class SendExchangeRequest(
    val from: Address,
    val exchangeId: String,
    val session: String
)

internal interface ApiService {
    // General
    @GET("get_balance")
    fun getBalance(@Query("session") session: String): Call<List<Balance>>
    @POST("create_account")
    fun createAccount(): Call<Account>

    // Payment
    @GET("get_payment_history")
    fun getPaymentHistory(@Query("session") session: String): Call<List<PaymentHistory>>

    // status: 201, error: 500
    @POST("send_payment")
    fun sendPayment(@Body body: SendPaymentRequestBody): Call<Payment>

    // Exchange
    @GET("get_exchange_offers")
    fun getExchangeOffers(): Call<List<ExchangeOffer>>
    @GET("get_exchange_history")
    fun getExchangeHistory(@Query("session") session: String): Call<List<ExchangeHistory>>

    // status: 201, error: 500
    @POST("send_exchange")
    fun sendExchange(@Body body: SendExchangeRequest): Call<Exchange>

    // status: 201, error: 500
    @POST("create_exchange_offer")
    fun createExchangeOffer(@Body body: CreateExchangeOfferRequest): Call<NewOffer>
}