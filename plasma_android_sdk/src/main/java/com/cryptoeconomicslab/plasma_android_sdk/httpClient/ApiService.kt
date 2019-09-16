package com.cryptoeconomicslab.plasma_android_sdk.httpClient

import com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity.*
import retrofit2.Call
import retrofit2.http.*

typealias Address = String

internal interface ApiService {
    // General
    @GET("get_balance")
    fun getBalance(address: Address): Call<Balance>
    @POST("create_account")
    fun createAccount(@Body password: String): Call<Account>

    // Payment
    @GET("get_payment_history")
    fun getPaymentHistory(address: Address): Call<List<PaymentHistory>>

    // status: 201, error: 500
    @POST("send_payment")
    fun sendPayment(@Body from: Address, amount: Int, tokenId: String, to: Address): Call<Unit>

    // Exchange
    @GET("get_exchange_offers")
    fun getExchangeOffers(): Call<List<ExchangeOffer>>
    @GET("get_exchange_history")
    fun getExchangeHistory(address: Address): Call<List<ExchangeHistory>>

    // status: 201, error: 500
    @POST("send_exchange")
    fun sendExchange(@Body from: Address, exchangeId: Int): Call<Unit>

    // status: 201, error: 500
    @POST("createNewExchangeOffer")
    fun createNewExchangeOffer(@Body from: Address, offer: ExchangeOffer): Call<Unit>
}