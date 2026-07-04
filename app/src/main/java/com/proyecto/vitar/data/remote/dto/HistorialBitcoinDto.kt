package com.proyecto.vitar.data.remote.dto

data class HistorialBitcoinDto(
    val id: String? = null,
    val usuarioId: String,
    val tipo: String, // COMPRA, VENTA
    val cantidadBTC: Double,
    val precioOperacion: Double,
    val moneda: String,
    val fecha: String
)
