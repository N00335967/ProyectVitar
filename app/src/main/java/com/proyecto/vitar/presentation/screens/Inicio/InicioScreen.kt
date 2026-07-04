package com.proyecto.vitar.presentation.screens.Inicio

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CurrencyBitcoin
import androidx.compose.material.icons.filled.StackedLineChart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.proyecto.vitar.core.navigation.NavRutas
import java.util.Locale

@Composable
fun InicioScreen(navController: NavController, vm: InicioViewModel) {

    val uiState by vm.uiState.collectAsState()
    val bitcoin = uiState.bitcoin
    val currency = uiState.selectedCurrency
    val btcBalance = uiState.btcBalance

    androidx.compose.runtime.LaunchedEffect(Unit) {
        vm.refresh()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7FB))
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        if (uiState.isLoading && bitcoin == null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Color(0xFF0B57FF))
            }
        } else {
            // Tarjeta Balance Total
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0B57FF)),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text(
                        text = "BALANCE TOTAL",
                        color = Color.White.copy(alpha = 0.8f),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(verticalAlignment = Alignment.Bottom) {
                        val balance = (bitcoin?.currentPrice ?: 0.0) * btcBalance
                        Text(
                            text = String.format(Locale.US, "%.2f", balance),
                            color = Color.White,
                            fontSize = 42.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = " ${currency.symbol}",
                            color = Color.White,
                            fontSize = 24.sp,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Surface(
                        shape = RoundedCornerShape(50),
                        color = Color.White.copy(alpha = 0.15f)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.StackedLineChart,
                                contentDescription = null,
                                tint = Color(0xFF7DFFB3),
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = String.format(Locale.US, "%+.2f%% hoy", bitcoin?.priceChangePercentage24h ?: 0.0),
                                color = Color(0xFF7DFFB3),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Tarjeta Bitcoin Principal
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate(NavRutas.DETALLEBITCOIN) },
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFFFFF4ED)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.CurrencyBitcoin,
                                    contentDescription = null,
                                    tint = Color(0xFFF7931A),
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(text = bitcoin?.name ?: "Bitcoin", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color(0xFF11224D))
                                Text(text = bitcoin?.symbol?.uppercase() ?: "BTC", color = Color.Gray, fontSize = 14.sp)
                            }
                        }

                        Column(horizontalAlignment = Alignment.End) {
                            Text(text = String.format(Locale.US, "%.2f %s", bitcoin?.currentPrice ?: 0.0, currency.symbol), fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF11224D))
                            Text(
                                text = String.format(Locale.US, "%+.2f%%", bitcoin?.priceChangePercentage24h ?: 0.0),
                                color = if ((bitcoin?.priceChangePercentage24h ?: 0.0) >= 0) Color(0xFF008A4B) else Color.Red,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Gráfico de barras simplificado
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp),
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalAlignment = Alignment.Bottom
                    ) {
                        val alturas = listOf(30, 25, 35, 40, 32, 50, 65, 75)
                        alturas.forEachIndexed { index, altura ->
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(altura.dp)
                                    .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
                                    .background(
                                        if (index >= 6) Color(0xFF00704A) else Color(0xFFB8D5C8)
                                    )
                            )
                        }
                    }

                    HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp), color = Color(0xFFF1F1F1))

                    Text(text = "CANTIDAD QUE POSEES", color = Color.Gray, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    
                    Spacer(modifier = Modifier.height(10.dp))

                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        color = Color(0xFFF1F4F9)
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = String.format(Locale.US, "%.5f", btcBalance), color = Color.Gray, fontSize = 16.sp)
                            Text(text = "BTC", color = Color.Gray, fontSize = 16.sp)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        color = Color(0xFFF3F0FF)
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Valor de tu inversión:", color = Color.Gray, fontSize = 15.sp)
                            val inversion = (bitcoin?.currentPrice ?: 0.0) * btcBalance
                            Text(text = String.format(Locale.US, "%.2f %s", inversion, currency.symbol), color = Color(0xFF2457FF), fontWeight = FontWeight.Bold, fontSize = 15.sp)
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Botones de Comprar y Vender (Estilo Cards)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(
                modifier = Modifier
                    .weight(1f)
                    .clickable { navController.navigate(NavRutas.COMPRARBITCOIN) },
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp).fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier.size(44.dp).background(Color(0xFF64FFB5).copy(alpha = 0.4f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "+", fontSize = 28.sp, color = Color(0xFF007A3D), fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "Comprar", fontSize = 16.sp, color = Color(0xFF11224D), fontWeight = FontWeight.Medium)
                }
            }

            Card(
                modifier = Modifier
                    .weight(1f)
                    .clickable { navController.navigate(NavRutas.VENDERBITCOIN) },
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp).fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier.size(44.dp).background(Color(0xFFFFE4DE), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "−", fontSize = 28.sp, color = Color(0xFF7A2A1A), fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "Vender", fontSize = 16.sp, color = Color(0xFF11224D), fontWeight = FontWeight.Medium)
                }
            }
        }

        Spacer(modifier = Modifier.height(100.dp))
    }
}
