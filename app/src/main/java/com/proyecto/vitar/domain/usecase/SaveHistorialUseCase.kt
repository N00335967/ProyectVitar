package com.proyecto.vitar.domain.usecase

import com.proyecto.vitar.domain.model.HistorialBitcoin
import com.proyecto.vitar.domain.repository.BitcoinRepository

class SaveHistorialUseCase(private val repository: BitcoinRepository) {
    suspend operator fun invoke(usuarioId: String, historial: HistorialBitcoin): Boolean {
        return repository.saveHistorial(usuarioId, historial)
    }
}
