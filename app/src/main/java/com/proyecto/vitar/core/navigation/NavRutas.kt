package com.proyecto.vitar.core.navigation

object NavRutas {
    const val INICIO = "inicio"
    const val HISTORIAL = "historial"
    const val PERFIL = "perfil"

    const val INICIARSESION= "IniciarSesion"
    const val REGISTRARSE= "Registrarse"
    const val RECUPERAR_PASSWORD= "recuperar_password"

    const val DETALLEBITCOIN = "detallebitcoin"

    const val COMPRARBITCOIN = "comprarbitcoin"

    const val VENDERBITCOIN = "venderbitcoin"

    fun getTitle(route: String?): String {
        return when (route) {
            INICIO -> "Inicio VITAR"
            HISTORIAL -> "Mi Historial"
            PERFIL -> "Mi Perfil"
            INICIARSESION -> "Iniciar Sesión"
            REGISTRARSE -> "Crear Cuenta"
            RECUPERAR_PASSWORD -> "Recuperar Contraseña"
            else -> "VITAR"
        }
    }
}
