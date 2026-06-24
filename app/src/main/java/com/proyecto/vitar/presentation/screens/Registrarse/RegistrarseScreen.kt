package com.proyecto.vitar.presentation.screens.Registrarse

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockReset
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.proyecto.vitar.core.navigation.NavRutas
import com.proyecto.vitar.presentation.screens.State.UsuarioUiEvent
import com.proyecto.vitar.presentation.viewmodel.UsuarioViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun RegistrarseScreen(navController: NavController, vm: UsuarioViewModel) {

    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmarPassword by remember { mutableStateOf("") }

    var mostrarPassword by remember { mutableStateOf(false) }
    var mostrarConfirmarPassword by remember { mutableStateOf(false) }


    val snackbarHostState = remember { SnackbarHostState() }

    //Esto hace que tome en cuenta los parametros que se regfistran,
    //osea como el nombre, correo y contraseña, se guardan
    LaunchedEffect(Unit) {
        vm.event.collectLatest { event ->
            when(event){
                is UsuarioUiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        event.mensaje
                    )
                }
                UsuarioUiEvent.NavigateLogin -> {

                    navController.navigate(
                        NavRutas.INICIARSESION
                    ) {
                        popUpTo(
                            NavRutas.REGISTRARSE
                        ) {
                            inclusive = true
                        }
                    }
                }
                else -> {}
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ){ paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF6F5FA))
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(50.dp))

            // Logo
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .size(45.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFF1147D9)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "G",
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "VITAR",
                    color = Color(0xFF1147D9),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Crea una cuenta",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E1E2D)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Crea una cuenta para comenzar a gestionar\ntus inversiones",
                textAlign = TextAlign.Center,
                color = Color.Gray,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(30.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                //campos para ingresar datos de registrarse
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {

                    // NOMBRE
                    Text(
                        text = "Nombre completo",
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    OutlinedTextField(
                        value = nombre,
                        onValueChange = { nombre = it },
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = {
                            Icon(Icons.Default.Person, null)
                        },
                        placeholder = {
                            Text("Ingresar nombre")
                        },
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // CORREO
                    Text(
                        text = "Correo electrónico",
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    OutlinedTextField(
                        value = correo,
                        onValueChange = { correo = it },
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = {
                            Icon(Icons.Default.Email, null)
                        },
                        placeholder = {
                            Text("Ingresar correo")
                        },
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // CONTRASEÑA
                    Text(
                        text = "Contraseña",
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        modifier = Modifier.fillMaxWidth(),

                        leadingIcon = {
                            Icon(Icons.Default.Lock, null)
                        },

                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    mostrarPassword = !mostrarPassword
                                }
                            ) {
                                Icon(
                                    imageVector =
                                        if (mostrarPassword)
                                            Icons.Default.VisibilityOff
                                        else
                                            Icons.Default.Visibility,
                                    contentDescription = null
                                )
                            }
                        },

                        visualTransformation =
                            if (mostrarPassword)
                                VisualTransformation.None
                            else
                                PasswordVisualTransformation(),

                        placeholder = {
                            Text("Ingresar contraseña")
                        },

                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // CONFIRMAR CONTRASEÑA
                    Text(
                        text = "Confirmar contraseña",
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    OutlinedTextField(
                        value = confirmarPassword,
                        onValueChange = { confirmarPassword = it },
                        modifier = Modifier.fillMaxWidth(),

                        leadingIcon = {
                            Icon(Icons.Default.LockReset, null)
                        },

                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    mostrarConfirmarPassword =
                                        !mostrarConfirmarPassword
                                }
                            ) {
                                Icon(
                                    imageVector =
                                        if (mostrarConfirmarPassword)
                                            Icons.Default.VisibilityOff
                                        else
                                            Icons.Default.Visibility,
                                    contentDescription = null
                                )
                            }
                        },

                        visualTransformation =
                            if (mostrarConfirmarPassword)
                                VisualTransformation.None
                            else
                                PasswordVisualTransformation(),

                        placeholder = {
                            Text("Confirmar contraseña")
                        },

                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    //este boton guarda o registra los datos ingresados
                    //para que se validen despues en de iniciar sesion
                    Button(
                        onClick = {
                            vm.registrarUsuario(
                                nombre = nombre,
                                correo = correo,
                                password = password,
                                confirmarPassword = confirmarPassword
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF1147D9)
                        ),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = "Crear cuenta",
                            fontSize = 20.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            Row {
                Text(
                    text = "¿Ya tienes una cuenta? ",
                    color = Color.Gray
                )

                Text(
                    text = "Iniciar sesión",
                    color = Color(0xFF1147D9),
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                    }
                )
            }

            Spacer(modifier = Modifier.height(25.dp))

            Text(
                text = "Al crear una cuenta, aceptas nuestros Términos de Servicio y la Política de Privacidad. Tu seguridad es nuestra prioridad institucional.",
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}