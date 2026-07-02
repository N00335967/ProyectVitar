package com.proyecto.vitar.domain.usecase

import com.proyecto.vitar.domain.model.Usuario
import com.proyecto.vitar.domain.repository.UsuarioRepository

class UpdateProfileUseCase(private val repository: UsuarioRepository) {
    suspend operator fun invoke(id: String, nuevoNombre: String): Usuario? {
        return repository.updateName(id, nuevoNombre)
    }
}
