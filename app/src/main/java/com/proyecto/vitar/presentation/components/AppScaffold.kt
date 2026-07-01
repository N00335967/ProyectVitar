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
import com.proyecto.vitar.core.navigation.BottomNavItem
import com.proyecto.vitar.core.navigation.NavRutas
import com.proyecto.vitar.presentation.event.EventBus
import com.proyecto.vitar.presentation.event.UiEvent

@Composable
fun AppScaffold(
    navHostController: NavHostController,
    mostrarBarras: Boolean, // <-- Agregamos esto para controlar cuándo se ve
    content: @Composable (PaddingValues) -> Unit
){
    val snackbarHostState = remember { SnackbarHostState() }
    val rutaActual = navHostController.currentBackStackEntryAsState().value?.destination?.route

    // 2. Buscamos el título correspondiente (puedes ajustar si es Perilf o Perfil)
    val listaOpcion = listOf(
        BottomNavItem.Inicio,
        BottomNavItem.Historial,
        BottomNavItem.Perilf
    )
    val tituloActual = listaOpcion.find { it.ruta == rutaActual }?.titulo ?: "Inicio"

    LaunchedEffect(Unit) {
        EventBus.events.collect { event ->
            when(event){
                is UiEvent.Success -> { snackbarHostState.showSnackbar(event.message) }
                is UiEvent.Error -> { snackbarHostState.showSnackbar(event.message) }
                is UiEvent.Warning -> { snackbarHostState.showSnackbar(event.message) }
            }
        }
    }

    Scaffold(
        topBar = {
            if (mostrarBarras) {
                AppTopBar(
                    titulo = tituloActual,
                    onMenuClick = { /* Acción del menú si lo usas más adelante */ },
                    onPerfilClick = {
                        if (rutaActual != NavRutas.PERFIL) {
                            navHostController.navigate(NavRutas.PERFIL) {
                                launchSingleTop = true
                            }
                        }
                    }
                )
            }
        },
        // Quitamos la topBar porque no la necesitas por ahora
        bottomBar = {
            if (mostrarBarras) { // <-- Si es true, dibuja tu barra
                AppBottomBar(navHostController)
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        content(paddingValues)
    }
}