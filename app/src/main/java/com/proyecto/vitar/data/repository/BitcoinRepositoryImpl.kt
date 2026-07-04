package com.proyecto.vitar.data.repository

import com.proyecto.vitar.data.mapper.toDto
import com.proyecto.vitar.data.mapper.toDomain
import com.proyecto.vitar.data.remote.datasource.BitcoinRemoteDataSource
import com.proyecto.vitar.domain.model.Bitcoin
import com.proyecto.vitar.domain.model.HistorialBitcoin
import com.proyecto.vitar.domain.repository.BitcoinRepository

class BitcoinRepositoryImpl(
    private val remoteDataSource: BitcoinRemoteDataSource
) : BitcoinRepository {

    // Plan B solo para Soles
    private val TASA_USD_A_PEN = 3.41

    override suspend fun getCoinsMarket(currency: String): List<Bitcoin> {
        val cleanCurrency = currency.lowercase().trim()
        
        return if (cleanCurrency == "pen") {
            val response = remoteDataSource.getCoinsMarket("usd")
            response?.map { dto ->
                val btc = dto.toDomain()
                btc.copy(
                    currentPrice = btc.currentPrice * TASA_USD_A_PEN,
                    marketCap = btc.marketCap * TASA_USD_A_PEN
                )
            } ?: emptyList()
        } else {
            val response = remoteDataSource.getCoinsMarket(cleanCurrency)
            response?.map { it.toDomain() } ?: emptyList()
        }
    }

    override suspend fun getBitcoinDetail(currency: String): Bitcoin {
        val cleanCurrency = currency.lowercase().trim()
        val response = remoteDataSource.getBitcoinDetail() ?: throw Exception("Error al obtener detalle")

        return if (cleanCurrency == "pen") {
            val btcUsd = response.toDomain("usd")
            btcUsd.copy(
                currentPrice = btcUsd.currentPrice * TASA_USD_A_PEN,
                marketCap = btcUsd.marketCap * TASA_USD_A_PEN,
                high24h = btcUsd.high24h * TASA_USD_A_PEN,
                low24h = btcUsd.low24h * TASA_USD_A_PEN,
                totalVolume = btcUsd.totalVolume * TASA_USD_A_PEN
            )
        } else {
            response.toDomain(cleanCurrency)
        }
    }

    override suspend fun getBitcoinPrice(currency: String): Double {
        val cleanCurrency = currency.lowercase().trim()
        
        return if (cleanCurrency == "pen") {
            val response = remoteDataSource.getBitcoinPrice("usd")
            (response?.bitcoin?.get("usd") ?: 0.0) * TASA_USD_A_PEN
        } else {
            val response = remoteDataSource.getBitcoinPrice(cleanCurrency)
            response?.bitcoin?.get(cleanCurrency) ?: 0.0
        }
    }

    override suspend fun getHistorial(usuarioId: String, currency: String): List<HistorialBitcoin> {
        val cleanCurrency = currency.lowercase().trim()
        val priceActual = getBitcoinPrice(cleanCurrency)
        val historyResponse = remoteDataSource.getHistorial(usuarioId)
        
        return historyResponse?.data?.map { dto ->
            val domain = dto.toDomain()
            val valorActual = domain.cantidadBTC * priceActual
            
            val precioOpFinal = if (currency.lowercase().trim() == "pen" && domain.moneda.lowercase() == "usd") {
                domain.precioOperacion * TASA_USD_A_PEN
            } else {
                domain.precioOperacion
            }

            val inversionInicial = domain.cantidadBTC * precioOpFinal
            val gananciaPerdida = if (domain.tipo == "COMPRA") valorActual - inversionInicial else inversionInicial - valorActual
            val rendimiento = if (inversionInicial > 0) (gananciaPerdida / inversionInicial) * 100 else 0.0

            domain.copy(
                precioActual = priceActual,
                valorActual = valorActual,
                gananciaPerdida = gananciaPerdida,
                rendimientoPorcentaje = rendimiento
            )
        } ?: emptyList()
    }

    override suspend fun saveHistorial(usuarioId: String, historial: HistorialBitcoin): Boolean {
        val dto = historial.toDto(usuarioId)
        val response = remoteDataSource.saveHistorial(dto)
        return response?.success == true
    }

    private fun convertPrice(priceUsd: Double, targetCurrency: String): Double {
        return when (targetCurrency.lowercase().trim()) {
            "pen" -> priceUsd * TASA_USD_A_PEN
            else -> priceUsd
        }
    }
}
