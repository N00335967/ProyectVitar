package com.proyecto.vitar.core.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.proyecto.vitar.di.AppContainer
import com.proyecto.vitar.presentation.screens.Historial.HistorialScreen
import com.proyecto.vitar.presentation.screens.IniciarSesion.IniciarSesionScreen
import com.proyecto.vitar.presentation.screens.Inicio.InicioScreen
import com.proyecto.vitar.presentation.screens.Perfil.PerfilScreen
import com.proyecto.vitar.presentation.screens.RecuperarPassword.RecuperarPasswordScreen
import com.proyecto.vitar.presentation.screens.Registrarse.RegistrarseScreen
import com.proyecto.vitar.presentation.screens.ComprarBitcoin.ComprarBitcoinPantalla
import com.proyecto.vitar.presentation.screens.VenderBitcoin.VenderBitcoinPantalla
import com.proyecto.vitar.presentation.screens.DetalleBitcoin.DetalleBitcoinPantalla

@Composable
fun AppNavigation(
    navController: NavHostController,
    container: AppContainer,
    padding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = NavRutas.INICIARSESION,
        modifier = Modifier.padding(padding)
    ) {
        composable(NavRutas.INICIARSESION) {
            IniciarSesionScreen(navController, container.iniciarSesionViewModel)
        }
        composable(NavRutas.REGISTRARSE) {
            RegistrarseScreen(navController, container.registrarseViewModel)
        }
        composable(NavRutas.INICIO) {
            InicioScreen(navController)
        }
        composable(NavRutas.HISTORIAL) {
            HistorialScreen()
        }
        composable(NavRutas.PERFIL) {
            PerfilScreen(navController, container.perfilViewModel)
        }
        composable(NavRutas.COMPRARBITCOIN) {
            ComprarBitcoinPantalla(navController, container.perfilViewModel)
        }
        composable(NavRutas.VENDERBITCOIN) {
            VenderBitcoinPantalla(navController, container.perfilViewModel)
        }
        composable(NavRutas.DETALLEBITCOIN) {
            DetalleBitcoinPantalla(navController)
        }
        composable(NavRutas.RECUPERAR_PASSWORD) {
            RecuperarPasswordScreen(navController)
        }
    }
}
