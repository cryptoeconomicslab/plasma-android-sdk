package com.cryptoeconomicslab.plasma_android_sdk.httpClient

import com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity.*
import retrofit2.Call
import retrofit2.http.*

typealias Address = String

internal interface ApiService {
    // General
    @GET("get_balance")
    fun getBalance(@Body address: Address): Call<List<Balance>>
    @POST("create_account")
    fun createAccount(): Call<Account>

    // Payment
    @GET("get_payment_history")
    fun getPaymentHistory(@Body address: Address): Call<List<PaymentHistory>>

    // status: 201, error: 500
    @POST("send_payment")
    fun sendPayment(@Body from: Address, amount: Int, tokenId: Int, to: Address): Call<Payment>

    // Exchange
    @GET("get_exchange_offers")
    fun getExchangeOffers(): Call<List<ExchangeOffer>>
    @GET("get_exchange_history")
    fun getExchangeHistory(@Body address: Address): Call<List<ExchangeHistory>>

    // status: 201, error: 500
    @POST("send_exchange")
    fun sendExchange(@Body from: Address, exchangeId: Int): Call<Exchange>

    // status: 201, error: 500
    @POST("create_exchange_offer")
    fun createExchangeOffer(@Body from: Address, offer: ExchangeOffer): Call<NewOffer>
}