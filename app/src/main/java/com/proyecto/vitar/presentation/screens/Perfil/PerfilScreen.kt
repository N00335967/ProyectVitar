package com.proyecto.vitar.presentation.screens.Perfil

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.proyecto.vitar.R
import com.proyecto.vitar.core.navigation.NavRutas
import com.proyecto.vitar.presentation.viewmodel.UsuarioViewModel

@Composable
fun PerfilScreen(navController: NavController, vm: UsuarioViewModel) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(10.dp))

        // FOTO DE PERFIL
        Box(
            contentAlignment = Alignment.BottomEnd
        ) {
            Image(
                painter = painterResource(id = R.drawable.homero),
                contentDescription = "homero",
                modifier = Modifier
                    .size(120.dp)
                    .border(//Le agrega un borde alrededor de la imagen
                        width = 2.dp,
                        color = Color(0xFF0000FF),
                        shape = CircleShape
                    )
                    .padding(4.dp)
                    .clip(CircleShape)
            )

            Box(
                modifier = Modifier
                    .size(30.dp)
                    .background(Color(0xFF0D6EFD), CircleShape)
                    .border(3.dp, Color.White, CircleShape)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = vm.obtenerNombre(),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = vm.obtenerCorreo(),
            color = Color.Gray,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        // TARJETA DE INVERSIÓN
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {

            Column(
                modifier = Modifier.padding(24.dp)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = "VALOR TOTAL ESTIMADO",
                        color = Color.Gray,
                        fontWeight = FontWeight.SemiBold
                    )

                    Box(
                        modifier = Modifier
                            .background(
                                Color(0xFFE8F7EF),
                                RoundedCornerShape(20.dp)
                            )
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "+4.2%",
                            color = Color(0xFF0A8F4D)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "1.42857",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "BTC",
                        color = Color(0xFF0D6EFD),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "≈ $92,450.00 USD",
                    color = Color.Gray
                )

                Divider(
                    modifier = Modifier.padding(vertical = 20.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Box(
                            modifier = Modifier
                                .size(10.dp)
                                .background(
                                    Color(0xFF0A8F4D),
                                    CircleShape
                                )
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = "Inversión Activa",
                            fontSize = 18.sp
                        )
                    }

                    Icon(
                        imageVector = Icons.Default.TrendingUp,
                        contentDescription = null,
                        tint = Color.Gray
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // OPCIONES
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {

            OpcionPerfil(
                Icons.Default.Edit,
                "Editar nombre"
            )

            Divider()

            OpcionPerfil(
                Icons.Default.Settings,
                "Configuración"
            )

            Divider()

            OpcionPerfil(
                Icons.Default.Notifications,
                "Notificaciones"
            )

            Divider()

            OpcionPerfil(
                icono = Icons.AutoMirrored.Filled.Logout,
                texto = "Cerrar sesión",
                colorTexto = Color.Red,
                {
                    vm.cerrarSesion()
                    navController.navigate(
                        NavRutas.INICIARSESION
                    ) {

                        popUpTo(0)
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // AYUDA
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFF1F1FF)
            )
        ) {

            Row(
                modifier = Modifier.padding(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(
                            Color(0xFFDDE2FF),
                            RoundedCornerShape(15.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.SupportAgent,
                        contentDescription = null,
                        tint = Color(0xFF4C6FFF)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column {

                    Text(
                        text = "¿Necesitas ayuda?",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )

                    Text(
                        text = "Soporte prioritario disponible 24/7 para inversores.",
                        color = Color.Gray
                    )
                }
            }
        }
        //Spacer(modifier = Modifier.height(100.dp))
    }
}

@Composable
//Lo mismo como el HistorialItem
//Es para reperir las mismas cosas con el mismo formato
//donde solo damos los datos correspondientes
fun OpcionPerfil(
    icono: ImageVector,
    texto: String,
    colorTexto: Color = Color.Black,
    onClick: () -> Unit = {}
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable() {
                onClick()
            }
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = icono,
            contentDescription = null,
            tint = colorTexto
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = texto,
            color = colorTexto,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = null,
            tint = Color.LightGray
        )
    }
}