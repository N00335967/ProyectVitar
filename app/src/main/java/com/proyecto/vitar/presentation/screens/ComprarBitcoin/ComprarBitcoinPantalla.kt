package com.proyecto.vitar.presentation.screens.ComprarBitcoin

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.proyecto.vitar.presentation.screens.Perfil.PerfilViewModel

@Composable
fun ComprarBitcoinPantalla(navController: NavController, vm: PerfilViewModel) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7FB))
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        Spacer(modifier = Modifier.height(10.dp))

        //==========================
        // TARJETA AZUL (PRECIO REAL)
        //==========================
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF0D47D9))
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "PRECIO EN TIEMPO REAL",
                        color = Color.White.copy(alpha = 0.8f),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .background(Color(0xFF1E88E5))
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Circle, null, tint = Color(0xFF5CFF8E), modifier = Modifier.size(8.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(text = "LIVE", color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(15.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "$64,281.45", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 38.sp)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "+2.4%", color = Color(0xFF5CFF8E), fontSize = 18.sp, fontWeight = FontWeight.Medium)
                }

                Text(text = "1 BTC = 59.412,20 €", color = Color.White.copy(alpha = 0.6f), fontSize = 16.sp)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        //==========================
        // CANTIDAD A COMPRAR
        //==========================
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(text = "CANTIDAD A COMPRAR", color = Color.Gray, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.Bottom) {
                        Text(text = "0.0150", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = Color(0xFF11224D))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = "BTC", color = Color(0xFF0D47D9), fontSize = 16.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 4.dp))
                    }
                    Box(modifier = Modifier.width(1.dp).height(40.dp).background(Color(0xFFEEEEEE)))
                    Column(modifier = Modifier.weight(1f).padding(start = 15.dp), horizontalAlignment = Alignment.End) {
                        Text(text = "≈ $964.22", fontWeight = FontWeight.Bold, fontSize = 26.sp, color = Color(0xFF11224D))
                        Text(text = "891,18 €", color = Color.Gray, fontSize = 14.sp)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        //==========================
        // MÉTODO Y COMISIÓN
        //==========================
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Card(
                modifier = Modifier.weight(1.5f),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "MÉTODO DE PAGO", color = Color.Gray, fontWeight = FontWeight.Bold, fontSize = 11.sp)
                    Spacer(modifier = Modifier.height(15.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier.size(40.dp).clip(RoundedCornerShape(10.dp)).background(Color(0xFFEAF0FF)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.AccountBalance, null, tint = Color(0xFF0D47D9), modifier = Modifier.size(20.dp))
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(text = "Transferencia SEPA", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color(0xFF11224D))
                            Text(text = "Finanza Bank **** 8821", color = Color.Gray, fontSize = 11.sp)
                        }
                    }
                }
            }

            Card(
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE9FFF4))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "COMISIÓN", color = Color(0xFF006D39), fontWeight = FontWeight.Bold, fontSize = 11.sp)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "0.50%", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF006D39))
                    Text(text = "$4.82 USD", color = Color(0xFF006D39).copy(alpha = 0.6f), fontSize = 12.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        //==========================================
        // RESUMEN DE TRANSACCIÓN
        //==========================================
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Resumen de transacción", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF11224D))
                    Icon(imageVector = Icons.Default.Circle, contentDescription = null, tint = Color.LightGray, modifier = Modifier.size(18.dp))
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "Subtotal", color = Color.Gray, fontSize = 15.sp)
                    Text(text = "$964.22", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color(0xFF11224D))
                }
                Spacer(modifier = Modifier.height(12.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "Comisión de Red", color = Color.Gray, fontSize = 15.sp)
                    Text(text = "$1.20", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color(0xFF11224D))
                }
                Spacer(modifier = Modifier.height(20.dp))
                Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color(0xFFF1F1F1)))
                Spacer(modifier = Modifier.height(20.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Total Final", fontWeight = FontWeight.Bold, fontSize = 17.sp, color = Color(0xFF11224D))
                    Text(text = "$970.24", color = Color(0xFF0D47D9), fontWeight = FontWeight.Bold, fontSize = 28.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        //==========================================
        // BOTÓN CONFIRMAR
        //==========================================
        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth().height(60.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0D47D9))
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.ShoppingCart, null, tint = Color.White, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "Confirmar Compra", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "Al confirmar, aceptas nuestros términos de servicio y la tasa de cambio actual garantizada por 30 segundos.",
            color = Color.Gray,
            fontSize = 13.sp,
            lineHeight = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 10.dp)
        )

        Spacer(modifier = Modifier.height(100.dp))
    }
}
