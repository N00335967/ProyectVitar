package com.proyecto.vitar.domain.usecase

import com.proyecto.vitar.domain.model.Bitcoin
import com.proyecto.vitar.domain.repository.BitcoinRepository

class GetCoinsMarketUseCase(private val repository: BitcoinRepository) {
    suspend operator fun invoke(currency: String): List<Bitcoin> {
        return repository.getCoinsMarket(currency)
    }
}
