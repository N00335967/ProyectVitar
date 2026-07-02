package com.proyecto.vitar.presentation.screens.IniciarSesion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proyecto.vitar.domain.usecase.UsuarioUseCases
import com.proyecto.vitar.presentation.event.EventBus
import com.proyecto.vitar.presentation.event.UiEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class IniciarSesionViewModel(
    private val useCases: UsuarioUseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow(IniciarSesionUiState())
    val uiState = _uiState.asStateFlow()

    fun iniciarSesion(correo: String, password: String) {
        viewModelScope.launch {
            if (correo.isBlank() || password.isBlank()) {
                EventBus.sendEvent(UiEvent.Warning("Por favor complete todos los campos"))
                return@launch
            }

            try {
                _uiState.value = _uiState.value.copy(isLoading = true)
                val usuario = useCases.login(correo, password)

                if (usuario != null) {
                    _uiState.value = _uiState.value.copy(isLoading = false, isSuccess = true)
                    EventBus.sendEvent(UiEvent.Success("Bienvenido ${usuario.nombre}"))
                } else {
                    _uiState.value = _uiState.value.copy(isLoading = false)
                    EventBus.sendEvent(UiEvent.Error("No se pudo iniciar sesión"))
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false)
                EventBus.sendEvent(UiEvent.Error(e.message ?: "Error de conexión"))
            }
        }
    }
}
