package com.proyecto.vitar.data.remote.datasource

import com.proyecto.vitar.core.network.ApiService
import com.proyecto.vitar.data.remote.dto.AuthResponse
import com.proyecto.vitar.data.remote.dto.LoginRequest

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun iniciarSesion(correo: String, password: String): AuthResponse {
        val request = LoginRequest(correo, password)
        return apiService.login(request)
    }
}