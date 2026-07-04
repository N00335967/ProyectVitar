package com.proyecto.vitar.domain.usecase

import com.proyecto.vitar.domain.repository.BitcoinRepository

class GetBitcoinPriceUseCase(private val repository: BitcoinRepository) {
    suspend operator fun invoke(currency: String): Double {
        return repository.getBitcoinPrice(currency)
    }
}
