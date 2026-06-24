package com.proyecto.vitar.presentation.screens.State

sealed class UsuarioUiEvent {

    data class ShowSnackbar(val mensaje: String) : UsuarioUiEvent()
    data object NavigateInicio : UsuarioUiEvent()

    data object NavigateLogin : UsuarioUiEvent()
}