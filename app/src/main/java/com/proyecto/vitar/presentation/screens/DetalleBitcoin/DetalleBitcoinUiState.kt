package com.proyecto.vitar.presentation.screens.DetalleBitcoin

import com.proyecto.vitar.core.util.Currency
import com.proyecto.vitar.domain.model.Bitcoin

data class DetalleBitcoinUiState(
    val isLoading: Boolean = false,
    val bitcoin: Bitcoin? = null,
    val selectedCurrency: Currency = Currency.USD,
    val error: String? = null
)
