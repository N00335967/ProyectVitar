package com.proyecto.vitar.di

import com.proyecto.vitar.domain.usecase.GetCurrentUserUseCase
import com.proyecto.vitar.domain.usecase.GetProfileUseCase
import com.proyecto.vitar.domain.usecase.LoginUseCase
import com.proyecto.vitar.domain.usecase.LogoutUseCase
import com.proyecto.vitar.domain.usecase.RegisterUseCase
import com.proyecto.vitar.domain.usecase.UpdateProfileUseCase
import com.proyecto.vitar.domain.usecase.UsuarioUseCases

class UseCaseModule(private val repositoryModule: RepositoryModule) {

    val usuarioUseCases by lazy {
        UsuarioUseCases(
            login = LoginUseCase(repositoryModule.usuarioRepository),
            register = RegisterUseCase(repositoryModule.usuarioRepository),
            updateProfile = UpdateProfileUseCase(repositoryModule.usuarioRepository),
            getProfile = GetProfileUseCase(repositoryModule.usuarioRepository),
            getCurrentUser = GetCurrentUserUseCase(repositoryModule.usuarioRepository),
            logout = LogoutUseCase(repositoryModule.usuarioRepository)
        )
    }
}
