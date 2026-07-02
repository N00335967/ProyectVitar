package com.proyecto.vitar.domain.usecase

import com.proyecto.vitar.domain.model.Usuario
import com.proyecto.vitar.domain.repository.UsuarioRepository

class RegisterUseCase(private val repository: UsuarioRepository) {
    suspend operator fun invoke(nombre: String, correo: String, password: String): Usuario? {
        return repository.register(nombre, correo, password)
    }
}
