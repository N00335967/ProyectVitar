package com.proyecto.vitar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.proyecto.vitar.core.navigation.AppNavigation
import com.proyecto.vitar.presentation.components.AppScaffold
import com.proyecto.vitar.ui.theme.VitarTheme

class MainActivity : ComponentActivity() {
    private val container by lazy {
        (application as VitarApplication).container
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VitarTheme {
                val navController = rememberNavController()

                AppScaffold(
                    navController = navController
                ) { padding ->
                    AppNavigation(
                        navController = navController,
                        container = container,
                        padding = padding
                    )
                }
            }
        }
    }
}
