package com.proyecto.vitar.presentation.screens.IniciarSesion

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
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
fun IniciarSesionScreen(navController: NavController, vm: UsuarioViewModel) {

    var correo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var mostrarPassword by remember { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {

        vm.event.collectLatest { event ->

            when(event){

                is UsuarioUiEvent.ShowSnackbar -> {

                    snackbarHostState.showSnackbar(
                        event.mensaje
                    )
                }

                UsuarioUiEvent.NavigateInicio -> {

                    navController.navigate(
                        NavRutas.INICIO
                    ) {

                        popUpTo(
                            NavRutas.INICIARSESION
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
    ) {paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF4F6F8))
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(60.dp))

            // Logo
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFF1147D9)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "₿",
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "VITAR",
                color = Color(0xFF1147D9),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Seguridad de nivel institucional para sus \n activos de Bitcoin.",
                textAlign = TextAlign.Center,
                color = Color.Gray,
                fontSize = 18.sp
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

                Column(
                    modifier = Modifier.padding(24.dp)
                ) {

                    Text(
                        text = "Bienvenido",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Medium
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Ingresa tus credenciales para acceder\na tu portafolio.",
                        color = Color.Gray,
                        fontSize = 16.sp
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    // CORREO
                    Text(
                        text = "CORREO ELECTRÓNICO",
                        fontWeight = FontWeight.Medium,
                        color = Color.DarkGray
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = correo,
                        onValueChange = { correo = it },
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = {
                            Icon(Icons.Default.Email, null)
                        },
                        placeholder = {
                            Text("nombre@ejemplo.com")
                        },
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // CONTRASEÑA + OLVIDASTE
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = "CONTRASEÑA",
                            fontWeight = FontWeight.Medium,
                            color = Color.DarkGray
                        )

                        Text(
                            text = "¿Olvidaste tu contraseña?",
                            color = Color(0xFF1147D9),
                            fontSize = 14.sp,
                            //SE AGREGO RECUPERAR CONTRASEÑA COMO CLICKEABLE
                            modifier = Modifier.clickable{
                                navController.navigate(NavRutas.RECUPERAR_PASSWORD)
                            }
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

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
                            Text("••••••••")
                        },

                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    //Valida los datos para ver si son correctos o no
                    //en caso de que sean erroneos dara un aviso
                    Button(
                        onClick = {
                            vm.iniciarSesion(
                                correo = correo,
                                password = password
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF1147D9)
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = "Iniciar sesión",
                            fontSize = 20.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(40.dp))

                    // DIVISOR
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        HorizontalDivider(
                            modifier = Modifier.weight(1f)
                        )

                        Text(
                            text = "  o continúa con  ",
                            color = Color.Gray
                        )

                        HorizontalDivider(
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    //otras formas de registrarse, (falta inmplementar)
                    //aun que no creo o si sobra tiempo
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        OutlinedButton(
                            onClick = { },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Google")
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        OutlinedButton(
                            onClick = { },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Biometría")
                        }
                    }

                    Spacer(modifier = Modifier.height(40.dp))

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = "¿No tienes una cuenta?",
                            color = Color.Gray
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Registrarse",
                            color = Color(0xFF1147D9),
                            modifier = Modifier.clickable{
                                navController.navigate(NavRutas.REGISTRARSE)
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            Row {

                Text(
                    text = "Privacidad",
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.width(20.dp))

                Text(
                    text = "Términos",
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.width(20.dp))

                Text(
                    text = "Seguridad",
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}