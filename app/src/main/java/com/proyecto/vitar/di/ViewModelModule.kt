package com.proyecto.vitar.di

import com.proyecto.vitar.presentation.screens.IniciarSesion.IniciarSesionViewModel
import com.proyecto.vitar.presentation.screens.Perfil.PerfilViewModel
import com.proyecto.vitar.presentation.screens.Registrarse.RegistrarseViewModel

class ViewModelModule(private val useCaseModule: UseCaseModule) {

    fun provideIniciarSesionViewModel(): IniciarSesionViewModel {
        return IniciarSesionViewModel(useCaseModule.usuarioUseCases)
    }

    fun provideRegistrarseViewModel(): RegistrarseViewModel {
        return RegistrarseViewModel(useCaseModule.usuarioUseCases)
    }

    fun providePerfilViewModel(): PerfilViewModel {
        return PerfilViewModel(useCaseModule.usuarioUseCases)
    }
}
