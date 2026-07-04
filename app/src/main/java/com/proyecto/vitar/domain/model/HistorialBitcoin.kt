package com.proyecto.vitar.domain.model

data class HistorialBitcoin(
    val id: String,
    val tipo: String, // COMPRA, VENTA
    val cantidadBTC: Double,
    val precioOperacion: Double,
    val moneda: String,
    val fecha: String,
    
    // Campos calculados
    val precioActual: Double = 0.0,
    val valorActual: Double = 0.0,
    val gananciaPerdida: Double = 0.0,
    val rendimientoPorcentaje: Double = 0.0
)
