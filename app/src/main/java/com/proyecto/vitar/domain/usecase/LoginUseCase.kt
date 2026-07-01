package com.proyecto.vitar.domain.usecase

import com.proyecto.vitar.domain.model.Usuario
import com.proyecto.vitar.domain.repository.UsuarioRepository

class LoginUseCase(private val repository: UsuarioRepository) {
    suspend operator fun invoke(correo: String, password: String): Usuario? {
        // Aquí poner lógica de negocio (ejm: validar que el correo tenga un '@')
        return repository.login(correo, password)
    }
}