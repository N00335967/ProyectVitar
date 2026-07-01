package com.proyecto.vitar.di

import android.content.Context
import com.proyecto.vitar.core.storage.SessionManager
import com.proyecto.vitar.presentation.viewmodel.UsuarioViewModel
import kotlin.getValue

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
    val viewModelModule by lazy {
        ViewModelModule(useCaseModule, sessionManager)
    }
    private val sessionManager by lazy { SessionManager(context) }
    val usuarioViewModel by lazy {
        UsuarioViewModel(sessionManager)
    }
}