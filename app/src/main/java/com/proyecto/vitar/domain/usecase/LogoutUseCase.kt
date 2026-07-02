package com.proyecto.vitar.domain.usecase

import com.proyecto.vitar.domain.repository.UsuarioRepository

class LogoutUseCase(private val repository: UsuarioRepository) {
    operator fun invoke() {
        repository.currentUser = null
    }
}
