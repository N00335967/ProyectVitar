package com.proyecto.vitar.data.remote.datasource

import com.proyecto.vitar.core.network.ApiService
import com.proyecto.vitar.data.remote.dto.AuthResponse
import com.proyecto.vitar.data.remote.dto.LoginRequest
import com.proyecto.vitar.data.remote.dto.UsuarioDto

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun iniciarSesion(correo: String, password: String): AuthResponse {
        /*val request = LoginRequest(correo, password)
        return apiService.login(request)
    }*/

        return AuthResponse(
            success = true,                          // Le decimos que el login fue un éxito
            message = "Login simulado con éxito",    // Mensaje para el EventBus/Snackbar
            usuario = UsuarioDto(                    // Creamos un usuario falso de prueba

                id = "1",
                nombre = "Usuario de Prueba",
                correo = "correo",
                password = "xd"
            )
        )
    }
}


