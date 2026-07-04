package com.proyecto.vitar.presentation.screens.Historial

import com.proyecto.vitar.core.util.Currency
import com.proyecto.vitar.domain.model.HistorialBitcoin

data class HistorialUiState(
    val isLoading: Boolean = false,
    val items: List<HistorialBitcoin> = emptyList(),
    val selectedCurrency: Currency = Currency.USD,
    val rendimientoTotal: Double = 0.0,
    val error: String? = null
)
