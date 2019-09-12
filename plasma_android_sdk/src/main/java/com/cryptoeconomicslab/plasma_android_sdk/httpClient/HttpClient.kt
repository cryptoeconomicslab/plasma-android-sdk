package com.cryptoeconomicslab.plasma_android_sdk.httpClient

import com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity.*
import com.cryptoeconomicslab.plasma_android_sdk.httpClient.error.NotFound
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

lateinit var retrofit: Retrofit

val httpBuilder: OkHttpClient.Builder get() {
    // create http client
    val httpClient = OkHttpClient.Builder()
        .addInterceptor(Interceptor { chain ->
            val original = chain.request()

            //header
            val request = original.newBuilder()
                .header("Accept", "application/json")
                .method(original.method(), original.body())
                .build()

            return@Interceptor chain.proceed(request)
        })
        .readTimeout(30, TimeUnit.SECONDS)

    // log interceptor
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    httpClient.addInterceptor(loggingInterceptor)

    return httpClient
}

fun <S> create(serviceClass:Class<S>): S {
    val gson = GsonBuilder()
        .serializeNulls()
        .create()

    retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl("http://127.0.0.1/")
        .client(httpBuilder.build())
        .build()

    return retrofit.create(serviceClass)
}

class HttpClient: HttpClientContract {
    companion object {
        // TODO: get API endpoint from environment variable
        private val instance: ApiService = create(ApiService::class.java)
    }

    // General
    // TODO: check if error handling is in right way.
    override fun getBalance(address: Address): Result<Balance> {
        try {
            val response = instance.getBalance(address).execute()
            val body = response.body()
            if (response.isSuccessful()) {
                if (body !== null) {
                    return Result.success(body)
                }
                return Result.failure(NotFound(response.errorBody().toString()))
            } else {
                return Result.failure(InternalError(response.errorBody().toString()))
            }
        } catch (e: IOException) {
            return Result.failure(InternalError("Internal Error"))
        }
    }

    override fun createAccount(password: String): Result<Account> {
        try {
            val response = instance.createAccount(password).execute()
            val body = response.body()
            if (response.isSuccessful()) {
                if (body !== null) {
                    return Result.success(body)
                }
                return Result.failure(InternalError(response.errorBody().toString()))
            } else {
                return Result.failure(InternalError(response.errorBody().toString()))

            }
        } catch (e: IOException) {
            return Result.failure(InternalError("Internal Error"))
        }

    }

    // Payment
    override fun getPaymentHistory(address: Address): Result<List<PaymentHistory>> {
        try {
            val response = instance.getPaymentHistory(address).execute()
            val body = response.body()
            if (response.isSuccessful()) {
                if (body !== null) {
                    return Result.success(body)
                }
                return Result.failure(NotFound(response.errorBody().toString()))
            } else {
                return Result.failure(InternalError(response.errorBody().toString()))
            }
        } catch (e: IOException) {
            return Result.failure(InternalError("Internal Error"))
        }
    }

    // status: 201, error: 500
    override fun sendPayment(from: Address, amount: Int, tokenId: String, to: Address): Result<Unit> {
        try {
            val response = instance.sendPayment(from, amount, tokenId, to).execute()
            if (response.isSuccessful()) {
                return Result.success(Unit)
            } else {
                return Result.failure(InternalError(response.errorBody().toString()))
            }
        } catch (e: IOException) {
            return Result.failure(InternalError("Internal Error"))
        }
    }

    // Exchange
    override fun getExchangeOffers(): Result<List<ExchangeOffer>> {
        try {
            val response = instance.getExchangeOffers().execute()
            val body = response.body()
            if (response.isSuccessful()) {
                if (body !== null) {
                    return Result.success(body)
                }
                return Result.failure(NotFound(response.errorBody().toString()))
            } else {
                return Result.failure(InternalError(response.errorBody().toString()))
            }
        } catch (e: IOException) {
            return Result.failure(InternalError("Internal Error"))
        }

    }
    override fun getExchangeHistory(address: Address): Result<List<ExchangeHistory>> {
        try {
            val response = instance.getExchangeHistory(address).execute()
            val body = response.body()
            if (response.isSuccessful()) {
                if (body !== null) {
                    return Result.success(body)
                }
                return Result.failure(NotFound(response.errorBody().toString()))
            } else {
                return Result.failure(InternalError(response.errorBody().toString()))
            }
        } catch (e: IOException) {
            return Result.failure(InternalError("Internal Error"))
        }

    }

    // status: 201, error: 500
    override fun sendExchange(from: Address, exchangeId: Int): Result<Unit> {
        try {
            val response = instance.sendExchange(from, exchangeId).execute()
            val body = response.body()
            if (response.isSuccessful()) {
                return Result.success(Unit)
            } else {
                return Result.failure(InternalError(response.errorBody().toString()))

            }
        } catch (e: IOException) {
            return Result.failure(InternalError("Internal Error"))
        }
    }

    // status: 201, error: 500
    override fun createNewExchangeOffer(from: Address, offer: ExchangeOffer): Result<Unit> {
        try {
            val response = instance.createNewExchangeOffer(from, offer).execute()
            val body = response.body()
            if (response.isSuccessful()) {
                return Result.success(Unit)
            } else {
                return Result.failure(InternalError(response.errorBody().toString()))
            }
        } catch (e: IOException) {
            return Result.failure(InternalError("Internal Error"))
        }
    }
}