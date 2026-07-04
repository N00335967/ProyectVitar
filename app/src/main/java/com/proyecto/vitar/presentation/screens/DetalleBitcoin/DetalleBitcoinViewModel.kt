package com.proyecto.vitar.presentation.screens.DetalleBitcoin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proyecto.vitar.core.util.CurrencyManager
import com.proyecto.vitar.domain.usecase.BitcoinUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DetalleBitcoinViewModel(
    private val bitcoinUseCases: BitcoinUseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetalleBitcoinUiState())
    val uiState = _uiState.asStateFlow()

    init {
        observeCurrencyAndFetchData()
    }

    private fun observeCurrencyAndFetchData() {
        viewModelScope.launch {
            CurrencyManager.selectedCurrency.collect { currency ->
                _uiState.value = _uiState.value.copy(
                    selectedCurrency = currency,
                    bitcoin = null,
                    isLoading = true
                )
                fetchBitcoinDetail(currency.code)
            }
        }
    }

    private fun fetchBitcoinDetail(currencyCode: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val btc = bitcoinUseCases.getBitcoinDetail(currencyCode)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    bitcoin = btc,
                    error = null
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }
}
