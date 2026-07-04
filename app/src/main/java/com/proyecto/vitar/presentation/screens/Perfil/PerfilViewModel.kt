package com.proyecto.vitar.presentation.screens.Perfil

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proyecto.vitar.core.util.Currency
import com.proyecto.vitar.core.util.CurrencyManager
import com.proyecto.vitar.domain.model.HistorialBitcoin
import com.proyecto.vitar.domain.usecase.BitcoinUseCases
import com.proyecto.vitar.domain.usecase.UsuarioUseCases
import com.proyecto.vitar.presentation.event.EventBus
import com.proyecto.vitar.presentation.event.UiEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PerfilViewModel(
    private val usuarioUseCases: UsuarioUseCases,
    private val bitcoinUseCases: BitcoinUseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow(PerfilUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadInitialData()
        observeCurrencyAndFetchPrice()
    }

    private fun loadInitialData() {
        val user = usuarioUseCases.getCurrentUser()
        if (user != null) {
            _uiState.value = _uiState.value.copy(
                id = user.id,
                nombre = user.nombre,
                correo = user.correo
            )
            loadBtcBalance(user.id)
        }
    }

    private fun loadBtcBalance(usuarioId: String) {
        viewModelScope.launch {
            try {
                // Obtenemos el historial de AWS para calcular el saldo real
                val history = bitcoinUseCases.getHistorial(usuarioId, "usd")
                var total = 1.42857 // Base inicial de la cuenta
                history.forEach { item ->
                    if (item.tipo == "COMPRA") total += item.cantidadBTC
                    else if (item.tipo == "VENTA") total -= item.cantidadBTC
                }
                _uiState.value = _uiState.value.copy(btcBalance = total)
            } catch (e: Exception) {
                // Si falla AWS, mantenemos el base
            }
        }
    }

    private fun observeCurrencyAndFetchPrice() {
        viewModelScope.launch {
            CurrencyManager.selectedCurrency.collectLatest { currency ->
                _uiState.value = _uiState.value.copy(selectedCurrency = currency)
                fetchBitcoinPrice(currency.code)
            }
        }
    }

    private fun fetchBitcoinPrice(currencyCode: String) {
        viewModelScope.launch {
            try {
                val price = bitcoinUseCases.getBitcoinPrice(currencyCode)
                _uiState.value = _uiState.value.copy(btcPrice = price)
            } catch (e: Exception) {
            }
        }
    }

    fun changeCurrency(newCurrency: Currency) {
        viewModelScope.launch {
            CurrencyManager.updateCurrency(newCurrency)
            EventBus.sendEvent(UiEvent.Success("Moneda cambiada a ${newCurrency.name}"))
        }
    }

    fun buyBitcoin(cantidad: Double) {
        viewModelScope.launch {
            try {
                val currentPrice = _uiState.value.btcPrice
                val currency = _uiState.value.selectedCurrency
                val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
                val currentDate = sdf.format(Date())

                val entry = HistorialBitcoin(
                    id = "",
                    tipo = "COMPRA",
                    cantidadBTC = cantidad,
                    precioOperacion = currentPrice,
                    moneda = currency.code,
                    fecha = currentDate
                )
                val success = bitcoinUseCases.saveHistorial(_uiState.value.id, entry)
                if (success) {
                    _uiState.value = _uiState.value.copy(btcBalance = _uiState.value.btcBalance + cantidad)
                    EventBus.sendEvent(UiEvent.Success("Compra registrada con éxito"))
                } else {
                    EventBus.sendEvent(UiEvent.Error("Error al registrar la compra"))
                }
            } catch (e: Exception) {
                EventBus.sendEvent(UiEvent.Error("Error: ${e.message}"))
            }
        }
    }

    fun sellBitcoin(cantidad: Double) {
        viewModelScope.launch {
            if (cantidad > _uiState.value.btcBalance) {
                EventBus.sendEvent(UiEvent.Warning("Saldo insuficiente"))
                return@launch
            }
            try {
                val currentPrice = _uiState.value.btcPrice
                val currency = _uiState.value.selectedCurrency
                val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
                val currentDate = sdf.format(Date())

                val entry = HistorialBitcoin(
                    id = "",
                    tipo = "VENTA",
                    cantidadBTC = cantidad,
                    precioOperacion = currentPrice,
                    moneda = currency.code,
                    fecha = currentDate
                )
                val success = bitcoinUseCases.saveHistorial(_uiState.value.id, entry)
                if (success) {
                    _uiState.value = _uiState.value.copy(btcBalance = _uiState.value.btcBalance - cantidad)
                    EventBus.sendEvent(UiEvent.Success("Venta registrada con éxito"))
                } else {
                    EventBus.sendEvent(UiEvent.Error("Error al registrar la venta"))
                }
            } catch (e: Exception) {
                EventBus.sendEvent(UiEvent.Error("Error: ${e.message}"))
            }
        }
    }

    fun loadProfile() {
        val currentId = _uiState.value.id
        if (currentId.isEmpty()) return

        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)
                val usuario = usuarioUseCases.getProfile(currentId)
                if (usuario != null) {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        nombre = usuario.nombre,
                        correo = usuario.correo
                    )
                } else {
                    _uiState.value = _uiState.value.copy(isLoading = false)
                    EventBus.sendEvent(UiEvent.Error("No se pudo cargar el perfil"))
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false)
                EventBus.sendEvent(UiEvent.Error("Error: ${e.message}"))
            }
        }
    }

    fun updateNombre(nuevoNombre: String) {
        viewModelScope.launch {
            if (nuevoNombre.isBlank()) {
                EventBus.sendEvent(UiEvent.Warning("El nombre no puede estar vacío"))
                return@launch
            }
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)
                val usuario = usuarioUseCases.updateProfile(_uiState.value.id, nuevoNombre)
                if (usuario != null) {
                    _uiState.value = _uiState.value.copy(isLoading = false, nombre = usuario.nombre)
                    EventBus.sendEvent(UiEvent.Success("Nombre actualizado"))
                } else {
                    _uiState.value = _uiState.value.copy(isLoading = false)
                    EventBus.sendEvent(UiEvent.Error("Error al actualizar"))
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false)
                EventBus.sendEvent(UiEvent.Error("Error: ${e.message}"))
            }
        }
    }

    fun logout() {
        usuarioUseCases.logout()
        _uiState.value = PerfilUiState()
    }

    fun refresh() {
        loadInitialData()
        observeCurrencyAndFetchPrice()
    }
}
