package com.proyecto.vitar.presentation.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.proyecto.vitar.core.navigation.BottomNavItem

@Composable
fun AppBottomBar(navController: NavHostController) {

    val menuItems = listOf(
        BottomNavItem.Inicio,
        BottomNavItem.Historial,
        BottomNavItem.Perilf // Mantengo el nombre exacto de tu archivo
    )

    // Esto detecta en qué pantalla está el usuario para iluminar el botón correcto
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        menuItems.forEach { item ->
            NavigationBarItem(
                label = { Text(text = item.titulo) },
                icon = { Icon(imageVector = item.icono, contentDescription = item.titulo) },
                selected = currentRoute == item.ruta,
                onClick = {
                    // Solo navega si el usuario no está ya en esa pantalla
                    if (currentRoute != item.ruta) {
                        navController.navigate(item.ruta) {
                            // Evita que se acumulen pantallas en el historial si presionas muchas veces
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}