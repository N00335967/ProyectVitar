package com.proyecto.vitar.domain.repository

import com.proyecto.vitar.domain.model.Usuario

interface UsuarioRepository {
    suspend fun login(correo: String, password: String): Usuario?
}