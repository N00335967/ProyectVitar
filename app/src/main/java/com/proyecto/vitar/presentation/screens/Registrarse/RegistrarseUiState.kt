package com.proyecto.vitar.presentation.screens.Registrarse

data class RegistrarseUiState(
    val nombre: String = "",
    val correo: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false
)
