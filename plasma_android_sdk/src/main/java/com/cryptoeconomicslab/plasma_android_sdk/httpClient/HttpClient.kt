package com.cryptoeconomicslab.plasma_android_sdk.httpClient

import android.util.Log
import com.cryptoeconomicslab.plasma_android_sdk.httpClient.entity.*
import com.cryptoeconomicslab.plasma_android_sdk.httpClient.error.ApplicationError
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

lateinit var retrofit: Retrofit

val httpBuilder: OkHttpClient.Builder
    get() {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(Interceptor(fun(chain: Interceptor.Chain): Response {
                val original = chain.request()

                //header
                val request = original.newBuilder()
                    .header("Accept", "application/json")
                    .method(original.method(), original.body())
                    .build()

                return chain.proceed(request)
            }))
            .readTimeout(30, TimeUnit.SECONDS)

        return httpClient
    }

internal fun <T> create(serviceClass: Class<T>): T {
    val gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .serializeNulls()
        .create()

    retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl("http://10.0.2.2:7777/")
        .client(httpBuilder.build())
        .build()

    return retrofit.create(serviceClass)
}

/**
 * HttpClient Class
 */
class HttpClient  {
    companion object {
        // TODO: get API endpoint from environment variable
        private val instance: ApiService = create(ApiService::class.java)
        private var session: String? = null
        private var address: Address? = null
    }

    fun loadSession(_session: String, _address: Address) {
        session = _session
        address = _address
    }

    // General
    // TODO: check if error handling is in right way.
    fun getBalance(): Result<List<Balance>> = try {
        if (session.isNullOrEmpty() || address.isNullOrEmpty()) {
            Result.failure<List<Balance>>(ApplicationError.SessionNotProvided("session not provided"))
        }

        val response = instance.getBalance(address!!, session!!).execute()
        val body = response.body()
        if (response.isSuccessful) {
            body?.let {
                return Result.success(it)
            }
            Result.failure(ApplicationError.NotFound(response.errorBody().toString()))
        } else {
            Result.failure(ApplicationError.InternalError(response.errorBody().toString()))
        }
    } catch (e: IOException) {
        Result.failure(InternalError("Internal Error"))
    }

    fun createAccount(): Result<Account> = try {
        val response = instance.createAccount().execute()
        val body = response.body()
        Log.d("request createAccount", body.toString())

        if (response.isSuccessful) {
            body?.let {
                session = it.session
                address = it.address

                return Result.success(it)
            }
        }
        Result.failure(ApplicationError.InternalError(response.errorBody().toString()))
    } catch (e: IOException) {
        Result.failure(ApplicationError.InternalError("Internal Error: calling create_account"))
    }

    fun getAddress(): Address? {
        return address
    }

    // Payment
    fun getPaymentHistory(address: Address): Result<List<PaymentHistory>> = try {
        val response = instance.getPaymentHistory(address).execute()
        val body = response.body()
        if (response.isSuccessful) {
            body?.let {
                return Result.success(it)
            }
            Result.failure(ApplicationError.NotFound(response.errorBody().toString()))
        } else {
            Result.failure(ApplicationError.InternalError(response.errorBody().toString()))
        }
    } catch (e: IOException) {
        Result.failure(ApplicationError.InternalError("Internal Error: calling get_payment_history"))
    }

    // status: 201, error: 500
    fun sendPayment(
        from: Address,
        amount: Int,
        tokenId: Int,
        to: Address
    ): Result<Payment> = try {
        val response = instance.sendPayment(from, amount, tokenId, to).execute()
        val body = response.body()
        if (response.isSuccessful) {
            body?.let {
                return Result.success(it)
            }
            Result.failure(ApplicationError.NotFound(response.errorBody().toString()))
        } else {
            Result.failure(ApplicationError.InternalError(response.errorBody().toString()))
        }
    } catch (e: IOException) {
        Result.failure(ApplicationError.InternalError("Internal Error: calling send_payment"))
    }

    // Exchange
    fun getExchangeOffers(): Result<List<ExchangeOffer>> = try {
        val response = instance.getExchangeOffers().execute()
        val body = response.body()
        if (response.isSuccessful) {
            body?.let {
                return Result.success(it)
            }
            Result.failure(ApplicationError.NotFound(response.errorBody().toString()))
        } else {
            Result.failure(ApplicationError.InternalError(response.errorBody().toString()))
        }
    } catch (e: IOException) {
        Result.failure(ApplicationError.InternalError("Internal Error: calling get_exchange_offers"))
    }

    fun getExchangeHistory(address: Address): Result<List<ExchangeHistory>> = try {
        val response = instance.getExchangeHistory(address).execute()
        val body = response.body()
        if (response.isSuccessful) {
            body?.let {
                return Result.success(it)
            }
            Result.failure(ApplicationError.NotFound(response.errorBody().toString()))
        } else {
            Result.failure(ApplicationError.InternalError(response.errorBody().toString()))
        }
    } catch (e: IOException) {
        Result.failure(ApplicationError.InternalError("Internal Error: calling get_exchange_history"))
    }

    // status: 201, error: 500
    fun sendExchange(from: Address, exchangeId: Int): Result<Exchange> = try {
        val response = instance.sendExchange(from, exchangeId).execute()
        val body = response.body()
        if (response.isSuccessful) {
            body?.let {
                return Result.success(it)
            }
            Result.failure(ApplicationError.NotFound(response.errorBody().toString()))
        } else {
            Result.failure(ApplicationError.InternalError(response.errorBody().toString()))

        }
    } catch (e: IOException) {
        Result.failure(ApplicationError.InternalError("Internal Error: calling send_exchange"))
    }

    // status: 201, error: 500
    fun createExchangeOffer(from: Address, offer: ExchangeOffer): Result<NewOffer> = try {
        val response = instance.createExchangeOffer(from, offer).execute()
        val body = response.body()
        if (response.isSuccessful) {
            body?.let {
                return Result.success(it)
            }
            Result.failure(ApplicationError.NotFound(response.errorBody().toString()))
        } else {
            Result.failure(ApplicationError.InternalError(response.errorBody().toString()))
        }
    } catch (e: IOException) {
        Result.failure(ApplicationError.InternalError("Internal Error: calling create_new_exchange_offer"))
    }
}