package com.proyecto.vitar.di

import com.proyecto.vitar.data.remote.datasource.BitcoinRemoteDataSource
import com.proyecto.vitar.data.remote.datasource.RemoteDataSource
import com.proyecto.vitar.data.repository.BitcoinRepositoryImpl
import com.proyecto.vitar.data.repository.UsuarioRepositoryImpl
import com.proyecto.vitar.domain.repository.BitcoinRepository
import com.proyecto.vitar.domain.repository.UsuarioRepository

class RepositoryModule(private val networkModule:NetworkModule) {
    private val remoteDataSource by lazy {
        RemoteDataSource(networkModule.apiService)
    }

    private val bitcoinRemoteDataSource by lazy {
        BitcoinRemoteDataSource(
            networkModule.bitcoinApiService,
            networkModule.bitcoinHistoryApiService
        )
    }

    // repositorio de Usuario
    val usuarioRepository: UsuarioRepository by lazy {
        UsuarioRepositoryImpl(remoteDataSource)
    }

    // repositorio de Bitcoin
    val bitcoinRepository: BitcoinRepository by lazy {
        BitcoinRepositoryImpl(bitcoinRemoteDataSource)
    }
}