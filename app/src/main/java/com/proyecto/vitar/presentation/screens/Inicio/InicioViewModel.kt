package com.proyecto.vitar.presentation.screens.Inicio

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proyecto.vitar.core.util.CurrencyManager
import com.proyecto.vitar.domain.usecase.BitcoinUseCases
import com.proyecto.vitar.domain.usecase.UsuarioUseCases
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class InicioViewModel(
    private val usuarioUseCases: UsuarioUseCases,
    private val bitcoinUseCases: BitcoinUseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow(InicioUiState())
    val uiState = _uiState.asStateFlow()

    init {
        observeCurrency()
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            val user = usuarioUseCases.getCurrentUser()
            if (user != null) {
                try {
                    val history = bitcoinUseCases.getHistorial(user.id, "usd")
                    var total = 1.42857
                    history.forEach { item ->
                        if (item.tipo == "COMPRA") total += item.cantidadBTC
                        else if (item.tipo == "VENTA") total -= item.cantidadBTC
                    }
                    _uiState.value = _uiState.value.copy(btcBalance = total)
                } catch (e: Exception) {}
            }
            fetchBitcoinData(CurrencyManager.selectedCurrency.value.code)
        }
    }

    fun refresh() {
        loadData()
    }

    private fun observeCurrency() {
        viewModelScope.launch {
            CurrencyManager.selectedCurrency.collect { currency ->
                _uiState.value = _uiState.value.copy(
                    selectedCurrency = currency,
                    isLoading = true
                )
                fetchBitcoinData(currency.code)
            }
        }
    }

    fun fetchBitcoinData(currencyCode: String) {
        viewModelScope.launch {
            try {
                val cleanCode = currencyCode.lowercase().trim()
                val marketsDeferred = async { bitcoinUseCases.getCoinsMarket(cleanCode) }
                val priceDeferred = async { bitcoinUseCases.getBitcoinPrice(cleanCode) }
                
                val btcFromMarket = marketsDeferred.await().firstOrNull()
                val exactPrice = priceDeferred.await()
                
                if (btcFromMarket != null) {
                    val finalBitcoin = btcFromMarket.copy(currentPrice = exactPrice)
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        bitcoin = finalBitcoin,
                        error = null
                    )
                } else {
                    _uiState.value = _uiState.value.copy(isLoading = false)
                }
            } catch (e: Exception) {
                Log.e("InicioViewModel", "Error", e)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }
}
