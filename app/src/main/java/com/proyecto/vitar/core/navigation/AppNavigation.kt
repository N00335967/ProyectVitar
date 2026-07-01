package com.proyecto.vitar.core.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.proyecto.vitar.di.AppContainer
import com.proyecto.vitar.presentation.components.AppScaffold
import com.proyecto.vitar.presentation.screens.Historial.HistorialScreen
import com.proyecto.vitar.presentation.screens.IniciarSesion.IniciarSesionScreen
import com.proyecto.vitar.presentation.screens.Inicio.InicioScreen
import com.proyecto.vitar.presentation.screens.Perfil.PerfilScreen
import com.proyecto.vitar.presentation.screens.RecuperarPassword.RecuperarPasswordScreen
import com.proyecto.vitar.presentation.screens.Registrarse.RegistrarseScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(container: AppContainer) {

    val navController = rememberNavController()

    val rutaActual = navController.currentBackStackEntryAsState().value?.destination?.route

    val mostrarBarras = rutaActual != NavRutas.INICIARSESION &&
            rutaActual != NavRutas.REGISTRARSE &&
            rutaActual != NavRutas.RECUPERAR_PASSWORD

    AppScaffold(
        navHostController = navController,
        mostrarBarras = mostrarBarras
    ) { paddingValues ->

        NavHost(
            navController = navController,
            modifier = Modifier.padding(paddingValues),
            startDestination = NavRutas.INICIARSESION
        ) {
            composable(NavRutas.INICIARSESION) { IniciarSesionScreen(navController, container.usuarioViewModel) }
            composable(NavRutas.REGISTRARSE) { RegistrarseScreen(navController, container.usuarioViewModel) }
            composable(NavRutas.INICIO) { InicioScreen() }
            composable(NavRutas.HISTORIAL) { HistorialScreen() }
            composable(NavRutas.PERFIL) { PerfilScreen(navController, container.usuarioViewModel) }
            composable(NavRutas.RECUPERAR_PASSWORD) { RecuperarPasswordScreen(navController) }
        }
    }
}