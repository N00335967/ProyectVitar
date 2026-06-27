package com.proyecto.vitar.di

import com.proyecto.vitar.core.network.ApiService
import com.proyecto.vitar.core.network.RetrofitClient

class NetworkModule {
    val apiService: ApiService by lazy {
        RetrofitClient.api
    }
}