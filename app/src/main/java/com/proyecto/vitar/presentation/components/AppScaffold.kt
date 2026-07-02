package com.proyecto.vitar.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.proyecto.vitar.core.navigation.NavRutas
import com.proyecto.vitar.presentation.event.EventBus
import com.proyecto.vitar.presentation.event.UiEvent

@Composable
fun AppScaffold(
    navController: NavHostController,
    content: @Composable (PaddingValues) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val rutaActual = navController.currentBackStackEntryAsState().value?.destination?.route

    val mostrarBarras = rutaActual != null && 
                        rutaActual != NavRutas.INICIARSESION && 
                        rutaActual != NavRutas.REGISTRARSE && 
                        rutaActual != NavRutas.RECUPERAR_PASSWORD

    LaunchedEffect(Unit) {
        EventBus.events.collect { event ->
            when(event) {
                is UiEvent.Success -> { snackbarHostState.showSnackbar(event.message) }
                is UiEvent.Error -> { snackbarHostState.showSnackbar(event.message) }
                is UiEvent.Warning -> { snackbarHostState.showSnackbar(event.message) }
            }
        }
    }

    Scaffold(
        topBar = {
            if (mostrarBarras) {
                AppTopBar(navController = navController)
            }
        },
        bottomBar = {
            if (mostrarBarras) {
                AppBottomBar(navController = navController)
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        content(paddingValues)
    }
}
