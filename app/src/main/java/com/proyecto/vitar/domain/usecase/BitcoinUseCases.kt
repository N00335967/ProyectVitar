package com.proyecto.vitar.domain.usecase

data class BitcoinUseCases(
    val getCoinsMarket: GetCoinsMarketUseCase,
    val getBitcoinDetail: GetBitcoinDetailUseCase,
    val getBitcoinPrice: GetBitcoinPriceUseCase,
    val getHistorial: GetHistorialUseCase,
    val saveHistorial: SaveHistorialUseCase
)
