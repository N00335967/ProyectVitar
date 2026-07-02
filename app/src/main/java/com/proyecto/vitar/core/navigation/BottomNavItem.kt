package com.proyecto.vitar.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Update
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem (val ruta: String, val titulo: String, val icono: ImageVector){
    data object Inicio: BottomNavItem(NavRutas.INICIO, "Inicio", Icons.Default.Home)
    data object Historial: BottomNavItem(NavRutas.HISTORIAL,"Historial", Icons.Default.Update)
    data object Perfil: BottomNavItem(NavRutas.PERFIL,"Perfil", Icons.Default.Person)
}