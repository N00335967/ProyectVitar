package com.proyecto.vitar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.proyecto.vitar.core.navigation.AppNavigation
import com.proyecto.vitar.core.storage.SessionManager
import com.proyecto.vitar.di.AppContainer
import com.proyecto.vitar.presentation.components.AppScaffold
import com.proyecto.vitar.presentation.viewmodel.UsuarioViewModel
import com.proyecto.vitar.ui.theme.VitarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val appContainer = (application as VitarApplication).container
        setContent {
            VitarTheme {
                AppNavigation(container = appContainer)
            }
        }
    }
}
