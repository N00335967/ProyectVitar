package com.proyecto.vitar.data.mapper

import com.proyecto.vitar.data.remote.dto.BitcoinDetailDto
import com.proyecto.vitar.data.remote.dto.CoinsMarketDto
import com.proyecto.vitar.data.remote.dto.HistorialBitcoinDto
import com.proyecto.vitar.domain.model.Bitcoin
import com.proyecto.vitar.domain.model.HistorialBitcoin

fun CoinsMarketDto.toDomain(): Bitcoin {
    return Bitcoin(
        id = id,
        symbol = symbol,
        name = name,
        image = image,
        currentPrice = currentPrice,
        marketCap = marketCap,
        priceChangePercentage24h = priceChangePercentage24h
    )
}

fun BitcoinDetailDto.toDomain(currency: String): Bitcoin {
    return Bitcoin(
        id = id,
        symbol = symbol,
        name = name,
        image = image.large,
        currentPrice = marketData.currentPrice[currency] ?: 0.0,
        marketCap = marketData.marketCap[currency] ?: 0.0,
        priceChangePercentage24h = marketData.priceChangePercentage24h,
        description = description.en,
        high24h = marketData.high24h[currency] ?: 0.0,
        low24h = marketData.low24h[currency] ?: 0.0,
        totalVolume = marketData.totalVolume[currency] ?: 0.0
    )
}

fun HistorialBitcoinDto.toDomain(): HistorialBitcoin {
    return HistorialBitcoin(
        id = id ?: "",
        tipo = tipo,
        cantidadBTC = cantidadBTC,
        precioOperacion = precioOperacion,
        moneda = moneda,
        fecha = fecha
    )
}

fun HistorialBitcoin.toDto(usuarioId: String): HistorialBitcoinDto {
    return HistorialBitcoinDto(
        usuarioId = usuarioId,
        tipo = tipo,
        cantidadBTC = cantidadBTC,
        precioOperacion = precioOperacion,
        moneda = moneda,
        fecha = fecha
    )
}
