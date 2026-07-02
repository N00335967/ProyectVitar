package com.proyecto.vitar.presentation.screens.Registrarse

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proyecto.vitar.domain.usecase.UsuarioUseCases
import com.proyecto.vitar.presentation.event.EventBus
import com.proyecto.vitar.presentation.event.UiEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegistrarseViewModel(
    private val useCases: UsuarioUseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegistrarseUiState())
    val uiState = _uiState.asStateFlow()

    fun onNombreChange(value: String) { _uiState.value = _uiState.value.copy(nombre = value) }
    fun onCorreoChange(value: String) { _uiState.value = _uiState.value.copy(correo = value) }
    fun onPasswordChange(value: String) { _uiState.value = _uiState.value.copy(password = value) }
    fun onConfirmPasswordChange(value: String) { _uiState.value = _uiState.value.copy(confirmPassword = value) }

    fun registrar() {
        viewModelScope.launch {
            if (!validateForm()) return@launch

            try {
                _uiState.value = _uiState.value.copy(isLoading = true)
                val usuario = useCases.register(
                    _uiState.value.nombre,
                    _uiState.value.correo,
                    _uiState.value.password
                )

                if (usuario != null) {
                    _uiState.value = _uiState.value.copy(isLoading = false, isSuccess = true)
                    EventBus.sendEvent(UiEvent.Success("Cuenta creada con éxito"))
                } else {
                    _uiState.value = _uiState.value.copy(isLoading = false)
                    EventBus.sendEvent(UiEvent.Error("No se pudo crear la cuenta"))
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false)
                EventBus.sendEvent(UiEvent.Error(e.message ?: "Error desconocido"))
            }
        }
    }

    private suspend fun validateForm(): Boolean {
        val state = _uiState.value
        if (state.nombre.isBlank() || state.correo.isBlank() || state.password.isBlank()) {
            EventBus.sendEvent(UiEvent.Warning("Por favor complete todos los campos"))
            return false
        }
        if (state.password != state.confirmPassword) {
            EventBus.sendEvent(UiEvent.Warning("Las contraseñas no coinciden"))
            return false
        }
        return true
    }
}
