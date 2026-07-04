package com.proyecto.vitar.presentation.screens.Historial

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Locale

@Composable
fun HistorialScreen(vm: HistorialViewModel) {

    val uiState by vm.uiState.collectAsState()
    val currency = uiState.selectedCurrency

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

        if (uiState.isLoading) {
            Box(modifier = Modifier.fillMaxWidth().height(200.dp), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Color(0xFF0D5BFF))
            }
        } else if (uiState.items.isEmpty()) {
            Text("No hay movimientos registrados", modifier = Modifier.padding(16.dp))
        } else {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column {
                    uiState.items.forEachIndexed { index, item ->
                        HistorialItem(
                            icono = if (item.tipo == "COMPRA") Icons.Default.TrendingUp else Icons.Default.SwapHoriz,
                            colorFondo = if (item.tipo == "COMPRA") Color(0xFFE7F8EC) else Color(0xFFFFF0EC),
                            colorIcono = if (item.tipo == "COMPRA") Color(0xFF0E8A58) else Color(0xFFD97706),
                            titulo = if (item.tipo == "COMPRA") "Compra de BTC" else "Venta de BTC",
                            fecha = item.fecha.replace("T", " ").substring(0, 16),
                            monto = String.format(Locale.US, "%.2f %s", item.cantidadBTC * item.precioActual, currency.symbol),
                            colorMonto = if (item.gananciaPerdida >= 0) Color(0xFF0E8A58) else Color.Red
                        )
                        if (index < uiState.items.size - 1) {
                            HorizontalDivider()
                        }
                    }
                }
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
                    text = "RENDIMIENTO TOTAL",
                    color = Color.White.copy(alpha = 0.8f)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = String.format(Locale.US, "%+.2f%% de rendimiento", uiState.rendimientoTotal),
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Tu portafolio se actualiza con el precio de CoinGecko en tiempo real.",
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(100.dp))
    }
}

@Composable
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
                .size(48.dp)
                .clip(CircleShape)
                .background(colorFondo),
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
