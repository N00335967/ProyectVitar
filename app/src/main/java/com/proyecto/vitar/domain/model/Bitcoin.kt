package com.proyecto.vitar.domain.model

data class Bitcoin(
    val id: String,
    val symbol: String,
    val name: String,
    val image: String,
    val currentPrice: Double,
    val marketCap: Double,
    val priceChangePercentage24h: Double,
    val description: String = "",
    val high24h: Double = 0.0,
    val low24h: Double = 0.0,
    val totalVolume: Double = 0.0
)
