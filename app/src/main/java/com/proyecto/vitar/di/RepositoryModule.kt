package com.proyecto.vitar.di

import com.proyecto.vitar.data.remote.datasource.RemoteDataSource
import com.proyecto.vitar.data.repository.UsuarioRepositoryImpl
import com.proyecto.vitar.domain.repository.UsuarioRepository

class RepositoryModule(private val networkModule:NetworkModule) {
    private val remoteDataSource by lazy {
        RemoteDataSource(networkModule.apiService)
    }
    // repositorio de Usuario
    val usuarioRepository: UsuarioRepository by lazy {
        UsuarioRepositoryImpl(remoteDataSource)
    }
}