package com.proyecto.vitar.data.remote.datasource

import com.proyecto.vitar.core.network.BitcoinApiService
import com.proyecto.vitar.data.remote.dto.*
import retrofit2.Response

class BitcoinRemoteDataSource(
    private val api: BitcoinApiService,
    private val historyApi: BitcoinApiService
) {
    suspend fun getCoinsMarket(currency: String): List<CoinsMarketDto>? {
        return api.getCoinsMarket(currency).body()
    }

    suspend fun getBitcoinDetail(): BitcoinDetailDto? {
        val response = api.getBitcoinDetail()
        return if (response.isSuccessful) response.body() else null
    }

    suspend fun getBitcoinPrice(currency: String): PriceDto? {
        return api.getBitcoinPrice(currency = currency).body()
    }

    suspend fun getHistorial(usuarioId: String): ApiResponse<List<HistorialBitcoinDto>>? {
        return historyApi.getHistorial(usuarioId).body()
    }

    suspend fun saveHistorial(request: HistorialBitcoinDto): ApiResponse<HistorialBitcoinDto>? {
        return historyApi.saveHistorial(request).body()
    }
}
