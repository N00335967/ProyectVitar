package com.proyecto.vitar.domain.model

data class Usuario(
    val id: String,
    val nombre: String,
    val correo: String
    // Nota: Por seguridad, no exponer la contraseña en el dominio
)