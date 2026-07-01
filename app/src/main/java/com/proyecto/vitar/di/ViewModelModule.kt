package com.proyecto.vitar.di

import com.proyecto.vitar.core.storage.SessionManager
import com.proyecto.vitar.presentation.viewmodel.UsuarioViewModel

class ViewModelModule(private val useCaseModule: UseCaseModule, private val sessionManager: SessionManager) {

    // Proveedor para el ViewModel de Autenticación / Login
    fun provideUsuarioViewModel(): UsuarioViewModel {
        return UsuarioViewModel(sessionManager)
    }

    // Más adelante, cuando agregues más pantallas, simplemente sumas sus funciones:
    // fun provideHomeViewModel(): HomeViewModel { ... }
}