package com.proyecto.vitar.core.storage

import android.content.Context

class SessionManager(context: Context) {

    private val prefs =
        context.getSharedPreferences(
            "vitar_prefs",
            Context.MODE_PRIVATE
        )

    fun registrarUsuario(
        nombre: String,
        correo: String,
        password: String
    ) {

        prefs.edit()
            .putString("nombre", nombre)
            .putString("correo", correo)
            .putString("password", password)
            .apply()
    }

    fun validarLogin(
        correo: String,
        password: String
    ): Boolean {

        val correoGuardado =
            prefs.getString("correo", "")

        val passwordGuardado =
            prefs.getString("password", "")

        val correcto =
            correo == correoGuardado &&
                    password == passwordGuardado

        if (correcto) {

            prefs.edit()
                .putBoolean(
                    "sesion_activa",
                    true
                )
                .apply()
        }

        return correcto
    }

    fun obtenerNombre(): String {

        return prefs.getString(
            "nombre",
            ""
        ) ?: ""
    }

    fun obtenerCorreo(): String {

        return prefs.getString(
            "correo",
            ""
        ) ?: ""
    }

    fun haySesionActiva(): Boolean {

        return prefs.getBoolean(
            "sesion_activa",
            false
        )
    }

    fun cerrarSesion() {
        prefs.edit()
            .putBoolean(
                "sesion_activa",
                false
            )
            .apply()
    }
}