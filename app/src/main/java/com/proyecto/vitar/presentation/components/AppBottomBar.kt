package com.proyecto.vitar.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.proyecto.vitar.core.navigation.BottomNavItem

@Composable
fun AppBottomBar(navController: NavHostController) {

    val menuItems = listOf(
        BottomNavItem.Inicio,
        BottomNavItem.Historial,
        BottomNavItem.Perfil
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp // Un poco de elevación para que se note la separación
    ) {
        menuItems.forEach { item ->
            val selected = currentRoute == item.ruta
            
            NavigationBarItem(
                selected = selected,
                alwaysShowLabel = true, // Siempre mostramos el texto abajo
                onClick = {
                    if (currentRoute != item.ruta) {
                        navController.navigate(item.ruta) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = {
                    if (selected) {
                        // Ajustamos el tamaño del "pill" verde para que no se corte
                        Box(
                            modifier = Modifier
                                .size(width = 64.dp, height = 32.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(Color(0xFF64FFB5)), // Verde neón
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = item.icono,
                                contentDescription = item.titulo,
                                tint = Color(0xFF11224D)
                            )
                        }
                    } else {
                        Icon(
                            imageVector = item.icono, 
                            contentDescription = item.titulo,
                            tint = Color.Gray
                        )
                    }
                },
                label = { 
                    Text(
                        text = item.titulo, 
                        fontSize = 12.sp,
                        color = if (selected) Color(0xFF11224D) else Color.Gray,
                        fontWeight = if (selected) androidx.compose.ui.text.font.FontWeight.Bold else androidx.compose.ui.text.font.FontWeight.Normal
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent, // Usamos nuestro Box personalizado
                    selectedIconColor = Color(0xFF11224D),
                    unselectedIconColor = Color.Gray
                )
            )
        }
    }
}
