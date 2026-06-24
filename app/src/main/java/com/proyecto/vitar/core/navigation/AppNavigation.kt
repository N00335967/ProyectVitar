package com.proyecto.vitar.core.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.proyecto.vitar.presentation.screens.Historial.HistorialScreen
import com.proyecto.vitar.presentation.screens.IniciarSesion.IniciarSesionScreen
import com.proyecto.vitar.presentation.screens.Inicio.InicioScreen
import com.proyecto.vitar.presentation.screens.Perfil.PerfilScreen
import com.proyecto.vitar.presentation.screens.Registrarse.RegistrarseScreen
import com.proyecto.vitar.presentation.viewmodel.UsuarioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(usuarioViewModel: UsuarioViewModel){
    val navController = rememberNavController()

    val listaOpcion = listOf(
        BottomNavItem.Inicio,
        BottomNavItem.Historial,
        BottomNavItem.Perilf
    )

    val rutaActual =
        navController.currentBackStackEntryAsState().value?.destination?.route

    val mostrarBarras = rutaActual != NavRutas.INICIARSESION &&
            rutaActual != NavRutas.REGISTRARSE

    val tituloActual = listaOpcion.find { it.ruta == rutaActual }?.titulo ?: "Inicio"

    Scaffold(

        topBar = {
            if (mostrarBarras) {
                TopAppBar(
                    title = {
                        Text(tituloActual)
                    },
                    navigationIcon = {
                        IconButton(onClick = { }) {
                            Icon(
                                Icons.Default.Menu,
                                contentDescription = "Menú"
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { }) {
                            Icon(
                                Icons.Default.AccountCircle,
                                contentDescription = "Perfil"
                            )
                        }
                    }
                )
            }
        },

        bottomBar = {
            if (mostrarBarras) {
                NavigationBar {
                    val rutaActual =
                        navController.currentBackStackEntryAsState().value?.destination?.route

                    listaOpcion.forEach { item ->
                        NavigationBarItem(
                            selected = rutaActual == item.ruta,
                            onClick = {
                                navController.navigate(item.ruta) {
                                    popUpTo(item.ruta)
                                    launchSingleTop = true
                                }
                            },
                            icon = {
                                Icon(item.icono, null)
                            },
                            label = {
                                Text(item.titulo)
                            }
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            modifier = Modifier.padding(paddingValues),
            startDestination = NavRutas.INICIARSESION
        ){
            composable (NavRutas.INICIARSESION){ IniciarSesionScreen(navController, usuarioViewModel) }
            composable (NavRutas.REGISTRARSE){ RegistrarseScreen(navController, usuarioViewModel) }
            composable (NavRutas.INICIO){ InicioScreen() }
            composable (NavRutas.HISTORIAL){ HistorialScreen() }
            composable (NavRutas.PERFIL){ PerfilScreen(navController, usuarioViewModel) }

        }
    }
}