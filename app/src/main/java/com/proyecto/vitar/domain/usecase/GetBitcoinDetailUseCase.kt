package com.proyecto.vitar.domain.usecase

import com.proyecto.vitar.domain.model.Bitcoin
import com.proyecto.vitar.domain.repository.BitcoinRepository

class GetBitcoinDetailUseCase(private val repository: BitcoinRepository) {
    suspend operator fun invoke(currency: String): Bitcoin {
        return repository.getBitcoinDetail(currency)
    }
}
