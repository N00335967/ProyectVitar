package com.proyecto.vitar.di

import com.proyecto.vitar.core.network.ApiService
import com.proyecto.vitar.core.network.BitcoinApiService
import com.proyecto.vitar.core.network.BitcoinRetrofit
import com.proyecto.vitar.core.network.RetrofitClient

class NetworkModule {
    val apiService: ApiService by lazy {
        RetrofitClient.api
    }

    val bitcoinApiService: BitcoinApiService by lazy {
        BitcoinRetrofit.api
    }

    val bitcoinHistoryApiService: BitcoinApiService by lazy {
        BitcoinRetrofit.historyApi
    }
}