package com.proyecto.vitar.di

import android.content.Context
import com.proyecto.vitar.presentation.screens.DetalleBitcoin.DetalleBitcoinViewModel
import com.proyecto.vitar.presentation.screens.Historial.HistorialViewModel
import com.proyecto.vitar.presentation.screens.IniciarSesion.IniciarSesionViewModel
import com.proyecto.vitar.presentation.screens.Inicio.InicioViewModel
import com.proyecto.vitar.presentation.screens.Perfil.PerfilViewModel
import com.proyecto.vitar.presentation.screens.Registrarse.RegistrarseViewModel

class AppContainer(private val context: Context) {

    private val networkModule by lazy {
        NetworkModule()
    }

    private val repositoryModule by lazy {
        RepositoryModule(networkModule)
    }

    private val useCaseModule by lazy {
        UseCaseModule(repositoryModule)
    }

    val iniciarSesionViewModel by lazy {
        IniciarSesionViewModel(useCaseModule.usuarioUseCases)
    }

    val registrarseViewModel by lazy {
        RegistrarseViewModel(useCaseModule.usuarioUseCases)
    }

    val perfilViewModel by lazy {
        PerfilViewModel(useCaseModule.usuarioUseCases, useCaseModule.bitcoinUseCases)
    }

    val inicioViewModel by lazy {
        InicioViewModel(useCaseModule.usuarioUseCases, useCaseModule.bitcoinUseCases)
    }

    val historialViewModel by lazy {
        HistorialViewModel(useCaseModule.usuarioUseCases, useCaseModule.bitcoinUseCases)
    }

    val detalleBitcoinViewModel by lazy {
        DetalleBitcoinViewModel(useCaseModule.bitcoinUseCases)
    }
}
