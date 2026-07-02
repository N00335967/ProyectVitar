package com.proyecto.vitar.data.remote.datasource

import android.util.Log
import com.google.gson.Gson
import com.proyecto.vitar.core.network.ApiService
import com.proyecto.vitar.data.remote.dto.ApiResponse
import com.proyecto.vitar.data.remote.dto.LoginRequest
import com.proyecto.vitar.data.remote.dto.RegisterRequest
import com.proyecto.vitar.data.remote.dto.UpdateUserRequest
import com.proyecto.vitar.data.remote.dto.UsuarioDto

class  RemoteDataSource(private val api: ApiService) {

    suspend fun login(request: LoginRequest): ApiResponse<UsuarioDto>? {
        val response = api.login(request)
        return if (response.isSuccessful) {
            response.body()
        } else {
            parseError(response.errorBody()?.string())
        }
    }

    suspend fun register(request: RegisterRequest): ApiResponse<UsuarioDto>? {
        val response = api.register(request)
        return if (response.isSuccessful) {
            response.body()
        } else {
            val errorBody = response.errorBody()?.string()
            Log.e("RemoteDataSource", "Register error: $errorBody")
            parseError(errorBody)
        }
    }

    private fun parseError(errorJson: String?): ApiResponse<UsuarioDto>? {
        return try {
            val apiResponse = Gson().fromJson(errorJson, ApiResponse::class.java) as? ApiResponse<UsuarioDto>
            
            // Si hay detalles de validación, mostrar el primero para guiar al usuario
            val detailMsg = apiResponse?.error?.details?.firstOrNull()?.let { 
                "Campo ${it.field}: ${it.message}"
            }

            // Traducción de mensajes comunes del servidor
            val translatedMessage = when (apiResponse?.message ?: apiResponse?.error?.message) {
                "Not Found" -> "Servidor no encontrado. Verifica la URL."
                "Validation failed" -> detailMsg ?: "Datos inválidos. Revisa los campos."
                "Invalid input" -> "Entrada inválida."
                "Internal Server Error" -> "Error interno del servidor."
                else -> null
            }

            if (translatedMessage != null) {
                apiResponse?.copy(message = translatedMessage)
            } else {
                apiResponse
            }
        } catch (e: Exception) {
            // Si no podemos parsear el JSON (a veces el error es texto plano), devolvemos un error genérico
            if (errorJson?.contains("Not Found") == true) {
                ApiResponse(success = false, message = "Error: Ruta no encontrada (404)")
            } else if (errorJson?.contains("Forbidden") == true) {
                ApiResponse(success = false, message = "Error: Acceso denegado (403). Revisa la URL.")
            } else {
                null
            }
        }
    }

    suspend fun updateUsuario(id: String, request: UpdateUserRequest): ApiResponse<UsuarioDto>? {
        val response = api.updateUsuario(id, request)
        return if (response.isSuccessful) response.body() else parseError(response.errorBody()?.string())
    }

    suspend fun getUsuarioById(id: String): ApiResponse<UsuarioDto>? {
        val response = api.getUsuarioById(id)
        return if (response.isSuccessful) response.body() else parseError(response.errorBody()?.string())
    }
}
