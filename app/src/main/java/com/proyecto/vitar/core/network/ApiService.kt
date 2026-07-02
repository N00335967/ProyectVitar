package com.proyecto.vitar.core.network

import com.proyecto.vitar.data.remote.dto.ApiResponse
import com.proyecto.vitar.data.remote.dto.LoginRequest
import com.proyecto.vitar.data.remote.dto.RegisterRequest
import com.proyecto.vitar.data.remote.dto.UpdateUserRequest
import com.proyecto.vitar.data.remote.dto.UsuarioDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @POST("login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<ApiResponse<UsuarioDto>>

    @POST("usuarios")
    suspend fun register(
        @Body request: RegisterRequest
    ): Response<ApiResponse<UsuarioDto>>

    @PUT("{id}")
    suspend fun updateUsuario(
        @Path("id") id: String,
        @Body request: UpdateUserRequest
    ): Response<ApiResponse<UsuarioDto>>

    @GET("{id}")
    suspend fun getUsuarioById(
        @Path("id") id: String
    ): Response<ApiResponse<UsuarioDto>>
}
