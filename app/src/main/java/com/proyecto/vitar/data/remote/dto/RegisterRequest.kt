package com.proyecto.vitar.data.remote.dto

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    val nombre: String,
    val correo: String,
    @SerializedName("contraseña")
    val password: String
)
