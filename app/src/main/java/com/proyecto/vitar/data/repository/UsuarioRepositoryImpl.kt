package com.proyecto.vitar.data.repository

import android.util.Log
import com.proyecto.vitar.data.mapper.toDomain
import com.proyecto.vitar.data.remote.datasource.RemoteDataSource
import com.proyecto.vitar.data.remote.dto.LoginRequest
import com.proyecto.vitar.data.remote.dto.RegisterRequest
import com.proyecto.vitar.data.remote.dto.UpdateUserRequest
import com.proyecto.vitar.domain.model.Usuario
import com.proyecto.vitar.domain.repository.UsuarioRepository

class UsuarioRepositoryImpl(private val remoteDataSource: RemoteDataSource) : UsuarioRepository {
    
    override var currentUser: Usuario? = null

    override suspend fun login(correo: String, password: String): Usuario? {
        val response = remoteDataSource.login(LoginRequest(correo = correo, password = password))
        if (response == null) throw Exception("Error de conexión al iniciar sesión")
        if (!response.success) {
            val errorMsg = response.message ?: response.error?.message ?: "Credenciales incorrectas"
            throw Exception(errorMsg)
        }
        currentUser = response.data?.toDomain()
        return currentUser
    }

    override suspend fun register(nombre: String, correo: String, password: String): Usuario? {
        val response = remoteDataSource.register(RegisterRequest(nombre, correo, password))
        Log.d("UsuarioRepository", "Register response: $response")
        if (response == null) throw Exception("Error de conexión al registrarse")
        if (!response.success) {
            val errorMsg = response.message ?: response.error?.message ?: "No se pudo crear la cuenta"
            throw Exception(errorMsg)
        }
        currentUser = response.data?.toDomain()
        return currentUser
    }

    override suspend fun updateName(id: String, nuevoNombre: String): Usuario? {
        val response = remoteDataSource.updateUsuario(id, UpdateUserRequest(nuevoNombre))
        if (response == null) throw Exception("Error de conexión al actualizar")
        if (!response.success) {
            val errorMsg = response.message ?: response.error?.message ?: "Error al actualizar nombre"
            throw Exception(errorMsg)
        }
        val updatedUser = response.data?.toDomain()
        if (updatedUser != null) {
            currentUser = updatedUser
        }
        return updatedUser
    }

    override suspend fun getProfile(id: String): Usuario? {
        val response = remoteDataSource.getUsuarioById(id)
        if (response == null) throw Exception("Error de conexión al obtener perfil")
        if (!response.success) {
            val errorMsg = response.message ?: response.error?.message ?: "Error al obtener perfil"
            throw Exception(errorMsg)
        }
        val user = response.data?.toDomain()
        if (user != null) {
            currentUser = user
        }
        return user
    }
}
