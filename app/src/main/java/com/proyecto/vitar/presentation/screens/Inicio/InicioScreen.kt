package com.proyecto.vitar.presentation.screens.Inicio

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.filled.StackedLineChart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InicioScreen() {

    Column(
        modifier = Modifier
            .fillMaxWidth()// Hace que el Column ocupe_todo el ancho disponible.
            .padding(16.dp)// Agrega espacio alrededor del contenido para que no quede pegado a los bordes.
            .verticalScroll(rememberScrollState()) // Permite desplazar la pantalla hacia
        // arriba y abajo cuando el contenido no cabe. Pero no lo baja
        //por comleto y se come algunas cosas
    ) {

        // Tarjeta Balance
        Card(// Card crea una tarjeta visual siguiendo Material Design.
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),// Redondea las esquinas de la tarjeta.
            colors = CardDefaults.cardColors(// Permite personalizar los colores de la Card.
                containerColor = Color(0xFF0B57FF)// Color de fondo de la tarjeta.
            )
        ) {
            Column(
                modifier = Modifier.padding(24.dp)
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "BALANCE TOTAL",
                    color = Color.White.copy(alpha = 0.8f),
                    // Color.White = color blanco.
                    // copy() crea una copia del color original modificando alguna propiedad.
                    // alpha controla la transparencia.
                    // 1f = completamente visible.
                    // 0f = completamente transparente.
                    // 0.8f = 80% visible y 20% transparente.
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "30.000,00 €",
                    color = Color.White,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(12.dp))

                Surface(
                    shape = RoundedCornerShape(50),
                    color = Color(0xFF1F6CFF)
                ) {
                    Row(
                        modifier = Modifier.padding(
                            horizontal = 12.dp,
                            vertical = 6.dp
                        ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.StackedLineChart,// Define qué icono se mostrará.
                            contentDescription = null,
                            // Texto para accesibilidad.
                            // Se usa para que lectores de pantalla describan el icono.
                            // Como el icono es solo decorativo, usamos null.
                            tint = Color(0xFF7DFFB3),// Cambia el color del icono.
                            modifier = Modifier.size(18.dp)
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        Text(
                            text = "+2.4% hoy",
                            color = Color(0xFF7DFFB3)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Tarjeta Bitcoin
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp)
        ) {

            Column(
                modifier = Modifier.padding(10.dp)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Column {
                        Text(
                            text = "Bitcoin",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "BTC",
                            color = Color.Gray
                        )
                    }

                    Column(
                        horizontalAlignment = Alignment.End
                    ) {

                        Text(
                            text = "60.000,00 €",
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "+1.25%",
                            color = Color(0xFF008A4B)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.Bottom
                ) {

                    val alturas = listOf(40, 30, 55, 45, 70, 60, 85, 100)

                    alturas.forEachIndexed { index, altura ->

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(altura.dp)
                                .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
                                .background(
                                    if (index >= 6)
                                        Color(0xFF007A3D)
                                    else
                                        Color(0xFFC7DDD5)
                                )
                        )
                    }
                }

                HorizontalDivider(
                    modifier = Modifier.padding(top = 8.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "CANTIDAD QUE POSEES",
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(8.dp))

                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF5F5F5)
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("0.5")
                        Text("BTC")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF3F0FF)
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                        // SpaceBetween distribuye los elementos horizontalmente.
                        // El primer elemento se coloca al inicio.
                        // El último elemento se coloca al final.
                        // Todo_el espacio restante se reparte entre ellos.
                    ) {

                        Text("Valor de tu inversión:")

                        Text(
                            text = "30.000,00 €",
                            color = Color(0xFF2457FF)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            Card(
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .background(
                                Color(0xFF63F0A4),
                                CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "+",
                            fontSize = 32.sp,
                            color = Color(0xFF007A3D)
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Comprar",
                        fontSize = 22.sp
                    )
                }
            }

            Card(
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .background(
                                Color(0xFFFFE4DE),
                                CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "−",
                            fontSize = 32.sp,
                            color = Color(0xFF7A2A1A)
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Vender",
                        fontSize = 22.sp
                    )
                }
            }
        }

        //Spacer(modifier = Modifier.height(100.dp))
    }
}