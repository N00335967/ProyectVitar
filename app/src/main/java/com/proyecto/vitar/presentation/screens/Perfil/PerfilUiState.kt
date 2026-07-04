package com.proyecto.vitar.presentation.screens.Perfil

import com.proyecto.vitar.core.util.Currency

data class PerfilUiState(
    val id: String = "",
    val nombre: String = "",
    val correo: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val selectedCurrency: Currency = Currency.USD,
    val btcPrice: Double = 0.0,
    val btcBalance: Double = 1.42857 // Saldo dinámico
)
