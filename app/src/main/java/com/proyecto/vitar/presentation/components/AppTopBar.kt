package com.proyecto.vitar.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.proyecto.vitar.R
import com.proyecto.vitar.core.navigation.NavRutas

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(navController: NavHostController) {
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val route = navBackStackEntry.value?.destination?.route
    
    val title = when(route) {
        NavRutas.DETALLEBITCOIN -> "Detalle de Bitcoin"
        NavRutas.COMPRARBITCOIN -> "Comprar Bitcoin"
        NavRutas.VENDERBITCOIN -> "Vender Bitcoin"
        else -> NavRutas.getTitle(route)
    }
    
    val showBackButton = route == NavRutas.DETALLEBITCOIN || 
                        route == NavRutas.COMPRARBITCOIN || 
                        route == NavRutas.VENDERBITCOIN

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                color = Color(0xFF0B57FF), // Azul brillante
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )
        },
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Volver",
                        tint = Color(0xFF0B57FF)
                    )
                }
            }
        },
        actions = {
            Image(
                painter = painterResource(id = R.drawable.homero),
                contentDescription = "Perfil",
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(36.dp)
                    .clip(CircleShape)
                    .border(1.dp, Color.LightGray, CircleShape),
                contentScale = ContentScale.Crop
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.White
        )
    )
}
