package com.proyecto.vitar.di

import com.proyecto.vitar.domain.usecase.BitcoinUseCases
import com.proyecto.vitar.domain.usecase.GetBitcoinDetailUseCase
import com.proyecto.vitar.domain.usecase.GetBitcoinPriceUseCase
import com.proyecto.vitar.domain.usecase.GetCoinsMarketUseCase
import com.proyecto.vitar.domain.usecase.GetCurrentUserUseCase
import com.proyecto.vitar.domain.usecase.GetHistorialUseCase
import com.proyecto.vitar.domain.usecase.GetProfileUseCase
import com.proyecto.vitar.domain.usecase.LoginUseCase
import com.proyecto.vitar.domain.usecase.LogoutUseCase
import com.proyecto.vitar.domain.usecase.RegisterUseCase
import com.proyecto.vitar.domain.usecase.SaveHistorialUseCase
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

    val bitcoinUseCases by lazy {
        BitcoinUseCases(
            getCoinsMarket = GetCoinsMarketUseCase(repositoryModule.bitcoinRepository),
            getBitcoinDetail = GetBitcoinDetailUseCase(repositoryModule.bitcoinRepository),
            getBitcoinPrice = GetBitcoinPriceUseCase(repositoryModule.bitcoinRepository),
            getHistorial = GetHistorialUseCase(repositoryModule.bitcoinRepository),
            saveHistorial = SaveHistorialUseCase(repositoryModule.bitcoinRepository)
        )
    }
}
