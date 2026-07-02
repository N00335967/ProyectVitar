package com.proyecto.vitar.domain.repository

import com.proyecto.vitar.domain.model.Usuario

interface UsuarioRepository {
    var currentUser: Usuario?
    suspend fun login(correo: String, password: String): Usuario?
    suspend fun register(nombre: String, correo: String, password: String): Usuario?
    suspend fun updateName(id: String, nuevoNombre: String): Usuario?
    suspend fun getProfile(id: String): Usuario?
}
