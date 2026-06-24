package com.proyecto.vitar.presentation.screens.Historial

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HistorialScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(24.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Text(
            text = "Historial de Inversión",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Seguimiento detallado de tus movimientos y actualizaciones de mercado.",
            fontSize = 16.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(18.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        ) {

            Column {

                HistorialItem(
                    icono = Icons.Default.TrendingUp,
                    colorFondo = Color(0xFFE7F8EC),
                    colorIcono = Color(0xFF0E8A58),
                    titulo = "Actualización de precio",
                    fecha = "Hoy, 10:45 AM",
                    monto = "$64,230.12",
                    colorMonto = Color(0xFF0E8A58)
                )

                HorizontalDivider()

                HistorialItem(
                    icono = Icons.Default.AccountBalanceWallet,
                    colorFondo = Color(0xFFEAF0FF),
                    colorIcono = Color(0xFF2563EB),
                    titulo = "Cambio de balance",
                    fecha = "Ayer, 08:20 PM",
                    monto = "$63,890.00",
                    colorMonto = Color.Black
                )

                HorizontalDivider()

                HistorialItem(
                    icono = Icons.Default.TrendingUp,
                    colorFondo = Color(0xFFE7F8EC),
                    colorIcono = Color(0xFF0E8A58),
                    titulo = "Actualización de precio",
                    fecha = "15 de Oct, 11:30 AM",
                    monto = "$62,150.45",
                    colorMonto = Color(0xFF0E8A58)
                )

                HorizontalDivider()

                HistorialItem(
                    icono = Icons.Default.SwapHoriz,
                    colorFondo = Color(0xFFFFF0EC),
                    colorIcono = Color(0xFFD97706),
                    titulo = "Cambio de balance",
                    fecha = "14 de Oct, 09:15 AM",
                    monto = "$61,900.00",
                    colorMonto = Color.Black
                )

                HorizontalDivider()

                HistorialItem(
                    icono = Icons.Default.TrendingUp,
                    colorFondo = Color(0xFFE7F8EC),
                    colorIcono = Color(0xFF0E8A58),
                    titulo = "Actualización de precio",
                    fecha = "13 de Oct, 04:50 PM",
                    monto = "$60,420.80",
                    colorMonto = Color(0xFF0E8A58)
                )
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF0D5BFF)
            )
        ) {

            Column(
                modifier = Modifier.padding(15.dp)
            ) {

                Text(
                    text = "RESUMEN SEMANAL",
                    color = Color.White.copy(alpha = 0.8f)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "+8.4% de rendimiento",
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Tu portafolio ha superado el promedio del mercado en las últimas 72 horas.",
                    color = Color.White
                )
            }
        }

        //Spacer(modifier = Modifier.height(100.dp))
    }
}

@Composable
//HistorialItem:
//Esta función representa un único registro dentro del historial.
//Se utiliza para reutilizar el mismo diseño en varios elementos
//del historial, cambiando únicamente los datos que recibe como parámetros.
fun HistorialItem(
    icono: ImageVector,
    colorFondo: Color,
    colorIcono: Color,
    titulo: String,
    fecha: String,
    monto: String,
    colorMonto: Color
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(48.dp) // Tamaño del círculo
                .clip(CircleShape)// Convierte la forma en un círculo
                .background(colorFondo),// Aplica el color de fondo
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icono,
                contentDescription = null,
                tint = colorIcono
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(
                text = titulo,
                fontSize = 18.sp
            )

            Text(
                text = fecha,
                color = Color.Gray,
                fontSize = 14.sp
            )
        }

        Text(
            text = monto,
            color = colorMonto,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
    }
}