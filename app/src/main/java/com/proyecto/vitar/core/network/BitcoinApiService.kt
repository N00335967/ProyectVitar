package com.proyecto.vitar.core.network

import com.proyecto.vitar.data.remote.dto.BitcoinDetailDto
import com.proyecto.vitar.data.remote.dto.CoinsMarketDto
import com.proyecto.vitar.data.remote.dto.HistorialBitcoinDto
import com.proyecto.vitar.data.remote.dto.PriceDto
import com.proyecto.vitar.data.remote.dto.ApiResponse
import com.proyecto.vitar.data.remote.dto.RegisterRequest
import retrofit2.Response
import retrofit2.http.*

interface BitcoinApiService {
    
    @GET("coins/markets")
    suspend fun getCoinsMarket(
        @Query("vs_currency") currency: String,
        @Query("ids") ids: String = "bitcoin"
    ): Response<List<CoinsMarketDto>>

    @GET("coins/bitcoin")
    suspend fun getBitcoinDetail(
        @Query("localization") localization: String = "false",
        @Query("tickers") tickers: Boolean = false,
        @Query("market_data") marketData: Boolean = true,
        @Query("community_data") communityData: Boolean = false,
        @Query("developer_data") developerData: Boolean = false,
        @Query("sparkline") sparkline: Boolean = false
    ): Response<BitcoinDetailDto>

    @GET("simple/price")
    suspend fun getBitcoinPrice(
        @Query("ids") ids: String = "bitcoin",
        @Query("vs_currencies") currency: String
    ): Response<PriceDto>

    // AWS History
    @GET("{usuarioId}")
    suspend fun getHistorial(
        @Path("usuarioId") usuarioId: String
    ): Response<ApiResponse<List<HistorialBitcoinDto>>>

    @POST("../historial")
    suspend fun saveHistorial(
        @Body request: HistorialBitcoinDto
    ): Response<ApiResponse<HistorialBitcoinDto>>
}
