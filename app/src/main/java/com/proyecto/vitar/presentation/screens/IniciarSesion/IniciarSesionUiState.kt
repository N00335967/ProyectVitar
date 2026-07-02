package com.proyecto.vitar.presentation.screens.IniciarSesion

data class IniciarSesionUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)
