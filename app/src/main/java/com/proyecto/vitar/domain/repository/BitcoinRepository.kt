package com.proyecto.vitar.domain.repository

import com.proyecto.vitar.domain.model.Bitcoin
import com.proyecto.vitar.domain.model.HistorialBitcoin

interface BitcoinRepository {
    suspend fun getCoinsMarket(currency: String): List<Bitcoin>
    suspend fun getBitcoinDetail(currency: String): Bitcoin
    suspend fun getBitcoinPrice(currency: String): Double
    suspend fun getHistorial(usuarioId: String, currency: String): List<HistorialBitcoin>
    suspend fun saveHistorial(usuarioId: String, historial: HistorialBitcoin): Boolean
}
