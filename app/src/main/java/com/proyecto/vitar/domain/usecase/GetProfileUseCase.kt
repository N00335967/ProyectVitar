package com.proyecto.vitar.domain.usecase

import com.proyecto.vitar.domain.model.Usuario
import com.proyecto.vitar.domain.repository.UsuarioRepository

class GetProfileUseCase(private val repository: UsuarioRepository) {
    suspend operator fun invoke(id: String): Usuario? {
        return repository.getProfile(id)
    }
}
