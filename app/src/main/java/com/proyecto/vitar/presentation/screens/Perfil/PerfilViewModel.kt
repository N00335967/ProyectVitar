package com.proyecto.vitar.presentation.screens.Perfil

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proyecto.vitar.domain.usecase.UsuarioUseCases
import com.proyecto.vitar.presentation.event.EventBus
import com.proyecto.vitar.presentation.event.UiEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PerfilViewModel(
    private val useCases: UsuarioUseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow(PerfilUiState())
    val uiState = _uiState.asStateFlow()

    init {
        val user = useCases.getCurrentUser()
        if (user != null) {
            _uiState.value = _uiState.value.copy(
                id = user.id,
                nombre = user.nombre,
                correo = user.correo
            )
        }
    }

    fun loadProfile() {
        val currentId = _uiState.value.id
        if (currentId.isEmpty()) return

        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)
                val usuario = useCases.getProfile(currentId)
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
                val usuario = useCases.updateProfile(_uiState.value.id, nuevoNombre)
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
        useCases.logout()
        _uiState.value = PerfilUiState()
    }
}
