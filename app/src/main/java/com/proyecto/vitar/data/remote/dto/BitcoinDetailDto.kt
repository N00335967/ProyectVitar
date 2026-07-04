package com.proyecto.vitar.data.remote.dto

import com.google.gson.annotations.SerializedName

data class BitcoinDetailDto(
    val id: String,
    val symbol: String,
    val name: String,
    val description: DescriptionDto,
    val image: ImageDto,
    @SerializedName("market_data") val marketData: MarketDataDto
)

data class DescriptionDto(val en: String)
data class ImageDto(val large: String)

data class MarketDataDto(
    @SerializedName("current_price") val currentPrice: Map<String, Double>,
    @SerializedName("market_cap") val marketCap: Map<String, Double>,
    @SerializedName("total_volume") val totalVolume: Map<String, Double>,
    @SerializedName("high_24h") val high24h: Map<String, Double>,
    @SerializedName("low_24h") val low24h: Map<String, Double>,
    @SerializedName("price_change_percentage_24h") val priceChangePercentage24h: Double
)
