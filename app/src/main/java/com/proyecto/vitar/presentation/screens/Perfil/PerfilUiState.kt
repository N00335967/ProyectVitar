package com.proyecto.vitar.presentation.screens.Perfil

data class PerfilUiState(
    val id: String = "",
    val nombre: String = "",
    val correo: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
