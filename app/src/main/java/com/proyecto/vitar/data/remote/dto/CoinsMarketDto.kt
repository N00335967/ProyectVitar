package com.proyecto.vitar.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CoinsMarketDto(
    val id: String,
    val symbol: String,
    val name: String,
    val image: String,
    @SerializedName("current_price") val currentPrice: Double,
    @SerializedName("market_cap") val marketCap: Double,
    @SerializedName("price_change_percentage_24h") val priceChangePercentage24h: Double
)
