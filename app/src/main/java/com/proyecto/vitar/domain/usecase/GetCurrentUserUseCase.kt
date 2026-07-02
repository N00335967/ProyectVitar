package com.proyecto.vitar.domain.usecase

import com.proyecto.vitar.domain.model.Usuario
import com.proyecto.vitar.domain.repository.UsuarioRepository

class GetCurrentUserUseCase(private val repository: UsuarioRepository) {
    operator fun invoke(): Usuario? {
        return repository.currentUser
    }
}
