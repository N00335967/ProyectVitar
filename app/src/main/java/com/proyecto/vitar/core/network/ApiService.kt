package com.proyecto.vitar.core.network

import com.proyecto.vitar.data.remote.dto.AuthResponse
import com.proyecto.vitar.data.remote.dto.LoginRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login") // O la configurada en AWS Lambda
    suspend fun login(
        @Body request: LoginRequest
    ): AuthResponse
}