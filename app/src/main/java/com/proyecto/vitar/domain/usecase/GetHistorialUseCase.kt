package com.proyecto.vitar.domain.usecase

import com.proyecto.vitar.domain.model.HistorialBitcoin
import com.proyecto.vitar.domain.repository.BitcoinRepository

class GetHistorialUseCase(private val repository: BitcoinRepository) {
    suspend operator fun invoke(usuarioId: String, currency: String): List<HistorialBitcoin> {
        return repository.getHistorial(usuarioId, currency)
    }
}
