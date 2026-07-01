package com.proyecto.vitar.di

import com.proyecto.vitar.domain.usecase.LoginUseCase
import com.proyecto.vitar.domain.usecase.UsuarioUseCases

class UseCaseModule(private val repositoryModule: RepositoryModule) {

    val usuarioUseCases by lazy {
        UsuarioUseCases(
            login = LoginUseCase(repositoryModule.usuarioRepository)
            // Cuando crees el registro, simplemente lo sumas aquí:
            // registrar = RegistrarUseCase(repositoryModule.usuarioRepository)
        )
    }
}