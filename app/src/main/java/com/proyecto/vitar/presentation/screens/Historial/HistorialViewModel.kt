package com.proyecto.vitar.presentation.screens.Historial

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proyecto.vitar.core.util.CurrencyManager
import com.proyecto.vitar.domain.usecase.BitcoinUseCases
import com.proyecto.vitar.domain.usecase.UsuarioUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HistorialViewModel(
    private val usuarioUseCases: UsuarioUseCases,
    private val bitcoinUseCases: BitcoinUseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow(HistorialUiState())
    val uiState = _uiState.asStateFlow()

    init {
        observeCurrencyAndFetchData()
    }

    private fun observeCurrencyAndFetchData() {
        viewModelScope.launch {
            CurrencyManager.selectedCurrency.collectLatest { currency ->
                _uiState.value = _uiState.value.copy(selectedCurrency = currency)
                fetchHistorial(currency.code)
            }
        }
    }

    private fun fetchHistorial(currencyCode: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val user = usuarioUseCases.getCurrentUser()
                if (user != null) {
                    val history = bitcoinUseCases.getHistorial(user.id, currencyCode)
                    
                    // Calcular rendimiento total promedio
                    val avgRendimiento = if (history.isNotEmpty()) {
                        history.map { it.rendimientoPorcentaje }.average()
                    } else 0.0

                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        items = history,
                        rendimientoTotal = avgRendimiento,
                        error = null
                    )
                } else {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = "Usuario no identificado"
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }
}
