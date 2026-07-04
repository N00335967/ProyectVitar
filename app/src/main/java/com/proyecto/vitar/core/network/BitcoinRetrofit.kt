package com.proyecto.vitar.core.network

import com.proyecto.vitar.core.util.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BitcoinRetrofit {
    
    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("x-cg-demo-api-key", Constants.COINGECKO_API_KEY)
                .build()
            chain.proceed(request)
        }
        .build()

    val api: BitcoinApiService by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.COINGECKO_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BitcoinApiService::class.java)
    }

    val historyApi: BitcoinApiService by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.HISTORY_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BitcoinApiService::class.java)
    }
}
