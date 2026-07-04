package com.proyecto.vitar.presentation.screens.DetalleBitcoin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CurrencyBitcoin
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.outlined.AddCircleOutline
import androidx.compose.material.icons.outlined.RemoveCircleOutline
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.proyecto.vitar.core.navigation.NavRutas
import java.util.Locale

@Composable
fun DetalleBitcoinPantalla(navController: NavController, vm: DetalleBitcoinViewModel) {

    val uiState by vm.uiState.collectAsState()
    val btc = uiState.bitcoin
    val currency = uiState.selectedCurrency

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7FB))
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        // Logo Bitcoin
        Box(
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)
                .background(Color(0xFF0D47D9)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.CurrencyBitcoin,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(35.dp)
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "${btc?.name?.uppercase() ?: "BITCOIN"} (${btc?.symbol?.uppercase() ?: "BTC"})",
            color = Color.Gray,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = String.format(Locale.US, "%.2f %s", btc?.currentPrice ?: 0.0, currency.symbol),
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF11224D)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(50))
                .background(Color(0xFF64FFB5).copy(alpha = 0.4f))
                .padding(horizontal = 12.dp, vertical = 4.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.TrendingUp, null, tint = Color(0xFF006D39), modifier = Modifier.size(14.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = String.format(Locale.US, "%+.2f%% (24h)", btc?.priceChangePercentage24h ?: 0.0),
                    color = Color(0xFF006D39),
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Card(
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(15.dp)) {
                    Text(text = "CAP. MERCADO", color = Color.Gray, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = String.format(Locale.US, "%.2fT", (btc?.marketCap ?: 0.0) / 1_000_000_000_000.0), fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF11224D))
                }
            }

            Card(
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(15.dp)) {
                    Text(text = "RANGO 24H", color = Color.Gray, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = String.format(Locale.US, "%.0fk - %.0fk", (btc?.low24h ?: 0.0)/1000, (btc?.high24h ?: 0.0)/1000), fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color(0xFF11224D))
                }
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Tendencia", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF11224D))
                    Text(text = "ÚLTIMAS 24 HORAS", color = Color.Gray, fontWeight = FontWeight.Bold, fontSize = 11.sp)
                }

                Spacer(modifier = Modifier.height(25.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.Bottom
                ) {
                    BarraGrafico(35.dp, Color(0xFFF1F2F6))
                    BarraGrafico(50.dp, Color(0xFFF1F2F6))
                    BarraGrafico(40.dp, Color(0xFFF1F2F6))
                    BarraGrafico(38.dp, Color(0xFFF1F2F6))
                    BarraGrafico(70.dp, Color(0xFF00704A))
                    BarraGrafico(85.dp, Color(0xFF00704A))
                    BarraGrafico(65.dp, Color(0xFF00704A))
                    BarraGrafico(95.dp, Color(0xFF00704A))
                    BarraGrafico(55.dp, Color(0xFF5AAE8F))
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = String.format(Locale.US, "$%.0f", btc?.low24h ?: 0.0), color = Color.Gray, fontSize = 12.sp)
                    Text(text = String.format(Locale.US, "$%.0f", btc?.currentPrice ?: 0.0), color = Color.Gray, fontSize = 12.sp)
                    Text(text = String.format(Locale.US, "$%.0f", btc?.high24h ?: 0.0), color = Color.Gray, fontSize = 12.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF0B57FF))
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                Text(text = "Tu Inversión", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "CANTIDAD INGRESADA", color = Color.White.copy(alpha = 0.7f), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(6.dp))
                val saldoEjemplo = 1.42857
                Text(text = String.format(Locale.US, "%.5f BTC", saldoEjemplo), color = Color.White, fontSize = 34.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(15.dp))
                Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color.White.copy(alpha = .3f)))
                Spacer(modifier = Modifier.height(15.dp))
                Text(text = "VALOR TOTAL CALCULADO", color = Color.White.copy(alpha = 0.7f), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val valorInversion = saldoEjemplo * (btc?.currentPrice ?: 0.0)
                    Text(text = String.format(Locale.US, "%.2f %s", valorInversion, currency.symbol), color = Color.White, fontWeight = FontWeight.Bold, fontSize = 38.sp)
                    Box(
                        modifier = Modifier.size(45.dp).clip(CircleShape).background(Color.White.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(imageVector = Icons.Default.TrendingUp, contentDescription = null, tint = Color.White)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Button(
                onClick = { navController.navigate(NavRutas.COMPRARBITCOIN) },
                modifier = Modifier.weight(1f).height(55.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0D47D9)),
                shape = RoundedCornerShape(15.dp)
            ) {
                Icon(imageVector = Icons.Outlined.AddCircleOutline, contentDescription = null, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Comprar", fontSize = 18.sp)
            }

            Button(
                onClick = { navController.navigate(NavRutas.VENDERBITCOIN) },
                modifier = Modifier.weight(1f).height(55.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE7E7EF), contentColor = Color.Black),
                shape = RoundedCornerShape(15.dp)
            ) {
                Icon(imageVector = Icons.Outlined.RemoveCircleOutline, contentDescription = null, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Vender", fontSize = 18.sp)
            }
        }

        Spacer(modifier = Modifier.height(100.dp))
    }
}

@Composable
fun BarraGrafico(altura: Dp, color: Color) {
    Box(modifier = Modifier.width(8.dp).height(altura).clip(RoundedCornerShape(50)).background(color))
}
