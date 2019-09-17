package com.cryptoeconomicslab.plasma_android_sdk.httpClient

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
        // create http client
        val httpClient = OkHttpClient.Builder()
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

        // log interceptor
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(loggingInterceptor)

        return httpClient
    }

internal fun <T> create(serviceClass: Class<T>): T {
    val gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .serializeNulls()
        .create()

    retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl("http://127.0.0.1:7777/")
        .client(httpBuilder.build())
        .build()

    return retrofit.create(serviceClass)
}

/**
 * HttpClient Class
 */
class HttpClient : HttpClientContract {
    companion object {
        // TODO: get API endpoint from environment variable
        private val instance: ApiService = create(ApiService::class.java)
    }

    // General
    // TODO: check if error handling is in right way.
    override fun getBalance(address: Address): Result<List<Balance>> = try {
        val response = instance.getBalance(address).execute()
        val body = response.body()
        if (response.isSuccessful) {
            body?.let {
                Result.success(it)
            }
            Result.failure(ApplicationError.NotFound(response.errorBody().toString()))
        } else {
            Result.failure(ApplicationError.InternalError(response.errorBody().toString()))
        }
    } catch (e: IOException) {
        Result.failure(InternalError("Internal Error"))
    }

    override fun createAccount(): Result<Account> = try {
        val response = instance.createAccount().execute()
        val body = response.body()
        if (response.isSuccessful) {
            body?.let {
                Result.success(it)
            }
            Result.failure(ApplicationError.InternalError(response.errorBody().toString()))
        } else {
            Result.failure(ApplicationError.InternalError(response.errorBody().toString()))
        }
    } catch (e: IOException) {
        Result.failure(ApplicationError.InternalError("Internal Error: calling create_account"))
    }

    // Payment
    override fun getPaymentHistory(address: Address): Result<List<PaymentHistory>> = try {
        val response = instance.getPaymentHistory(address).execute()
        val body = response.body()
        if (response.isSuccessful) {
            body?.let {
                Result.success(it)
            }
            Result.failure(ApplicationError.NotFound(response.errorBody().toString()))
        } else {
            Result.failure(ApplicationError.InternalError(response.errorBody().toString()))
        }
    } catch (e: IOException) {
        Result.failure(ApplicationError.InternalError("Internal Error: calling get_payment_history"))
    }

    // status: 201, error: 500
    override fun sendPayment(
        from: Address,
        amount: Int,
        tokenId: Int,
        to: Address
    ): Result<Payment> = try {
        val response = instance.sendPayment(from, amount, tokenId, to).execute()
        val body = response.body()
        if (response.isSuccessful) {
            body?.let {
                Result.success(it)
            }
        } else {
            Result.failure(ApplicationError.InternalError(response.errorBody().toString()))
        }
    } catch (e: IOException) {
        Result.failure(ApplicationError.InternalError("Internal Error: calling send_payment"))
    }

    // Exchange
    override fun getExchangeOffers(): Result<List<ExchangeOffer>> = try {
        val response = instance.getExchangeOffers().execute()
        val body = response.body()
        if (response.isSuccessful) {
            body?.let {
                Result.success(it)
            }
            Result.failure(ApplicationError.NotFound(response.errorBody().toString()))
        } else {
            Result.failure(ApplicationError.InternalError(response.errorBody().toString()))
        }
    } catch (e: IOException) {
        Result.failure(ApplicationError.InternalError("Internal Error: calling get_exchange_offers"))
    }

    override fun getExchangeHistory(address: Address): Result<List<ExchangeHistory>> = try {
        val response = instance.getExchangeHistory(address).execute()
        val body = response.body()
        if (response.isSuccessful) {
            body?.let {
                Result.success(it)
            }
            Result.failure(ApplicationError.NotFound(response.errorBody().toString()))
        } else {
            Result.failure(ApplicationError.InternalError(response.errorBody().toString()))
        }
    } catch (e: IOException) {
        Result.failure(ApplicationError.InternalError("Internal Error: calling get_exchange_history"))
    }

    // status: 201, error: 500
    override fun sendExchange(from: Address, exchangeId: Int): Result<Exchange> = try {
        val response = instance.sendExchange(from, exchangeId).execute()
        val body = response.body()
        if (response.isSuccessful) {
            body?.let {
                Result.success(it)
            }
        } else {
            Result.failure(ApplicationError.InternalError(response.errorBody().toString()))

        }
    } catch (e: IOException) {
        Result.failure(ApplicationError.InternalError("Internal Error: calling send_exchange"))
    }

    // status: 201, error: 500
    override fun createExchangeOffer(from: Address, offer: ExchangeOffer): Result<NewOffer> = try {
        val response = instance.createExchangeOffer(from, offer).execute()
        val body = response.body()
        if (response.isSuccessful) {
            body?.let {
                Result.success(it)
            }
        } else {
            Result.failure(ApplicationError.InternalError(response.errorBody().toString()))
        }
    } catch (e: IOException) {
        Result.failure(ApplicationError.InternalError("Internal Error: calling create_new_exchange_offer"))
    }
}