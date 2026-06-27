package com.proyecto.vitar.data.remote.dto

data class AuthResponse(
    val success: Boolean,
    val message: String,
    val usuario: UsuarioDto?
)