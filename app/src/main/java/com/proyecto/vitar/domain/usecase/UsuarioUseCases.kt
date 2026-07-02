package com.proyecto.vitar.domain.usecase

data class UsuarioUseCases(
    val login: LoginUseCase,
    val register: RegisterUseCase,
    val updateProfile: UpdateProfileUseCase,
    val getProfile: GetProfileUseCase,
    val getCurrentUser: GetCurrentUserUseCase,
    val logout: LogoutUseCase
)
