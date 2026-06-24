package com.proyecto.vitar.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proyecto.vitar.core.storage.SessionManager
import com.proyecto.vitar.presentation.screens.State.UsuarioUiEvent
import com.proyecto.vitar.presentation.screens.State.UsuarioUiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UsuarioViewModel(
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _uiState =
        MutableStateFlow(UsuarioUiState())

    val uiState: StateFlow<UsuarioUiState>
            = _uiState

    private val _event =
        MutableSharedFlow<UsuarioUiEvent>()

    val event = _event

    fun registrarUsuario(
        nombre: String,
        correo: String,
        password: String,
        confirmarPassword: String
    ) {

        viewModelScope.launch {

            if (
                nombre.isBlank() ||
                correo.isBlank() ||
                password.isBlank() ||
                confirmarPassword.isBlank()
            ) {

                _event.emit(
                    UsuarioUiEvent.ShowSnackbar(
                        "Complete todos los campos"
                    )
                )
                return@launch
            }

            if (
                password != confirmarPassword
            ) {

                _event.emit(
                    UsuarioUiEvent.ShowSnackbar(
                        "Las contraseñas no coinciden"
                    )
                )
                return@launch
            }

            sessionManager.registrarUsuario(
                nombre,
                correo,
                password
            )

            _event.emit(
                UsuarioUiEvent.ShowSnackbar(
                    "Cuenta creada correctamente"
                )
            )

            _event.emit(
                UsuarioUiEvent.NavigateLogin
            )
        }
    }

    fun iniciarSesion(
        correo: String,
        password: String
    ) {

        viewModelScope.launch {

            if (
                correo.isBlank() ||
                password.isBlank()
            ) {

                _event.emit(
                    UsuarioUiEvent.ShowSnackbar(
                        "Complete todos los campos"
                    )
                )

                return@launch
            }

            val loginCorrecto =
                sessionManager.validarLogin(
                    correo,
                    password
                )

            if (loginCorrecto) {

                _event.emit(
                    UsuarioUiEvent.NavigateInicio
                )

            } else {

                _event.emit(
                    UsuarioUiEvent.ShowSnackbar(
                        "Correo o contraseña incorrectos"
                    )
                )
            }
        }
    }

    fun cerrarSesion() {

        sessionManager.cerrarSesion()
    }

    fun obtenerNombre(): String {

        return sessionManager.obtenerNombre()
    }

    fun obtenerCorreo(): String {

        return sessionManager.obtenerCorreo()
    }
}