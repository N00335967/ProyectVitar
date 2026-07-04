package com.proyecto.vitar.presentation.screens.VenderBitcoin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.proyecto.vitar.presentation.screens.Perfil.PerfilViewModel
import java.util.Locale

@Composable
fun VenderBitcoinPantalla(navController: NavController, vm: PerfilViewModel) {

    val uiState by vm.uiState.collectAsState()
    val btcPrice = uiState.btcPrice
    val currency = uiState.selectedCurrency

    var cantidad by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7FB))
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        //==========================
        // SALDO DISPONIBLE
        //==========================

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF0D47D9)
            )
        ) {

            Column(
                modifier = Modifier.padding(24.dp)
            ) {

                Text(
                    text = "SALDO DISPONIBLE",
                    color = Color.White.copy(alpha = .8f),
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(15.dp))

                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    // Usamos el saldo del ejemplo en Perfil: 1.42857
                    Text(
                        text = "1.42857",
                        color = Color.White,
                        fontSize = 44.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "BTC",
                        color = Color.White,
                        fontSize = 22.sp
                    )

                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = String.format(Locale.US, "≈ %.2f %s", 1.42857 * btcPrice, currency.symbol),
                    color = Color.White.copy(alpha = .8f),
                    fontSize = 18.sp
                )

            }

        }

        Spacer(modifier = Modifier.height(24.dp))

        //==========================
        // CANTIDAD A VENDER
        //==========================

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

                Text(
                    text = "CANTIDAD A VENDER",
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(28.dp))

                BasicTextField(
                    value = cantidad,
                    onValueChange = { if (it.all { char -> char.isDigit() || char == '.' }) cantidad = it },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = androidx.compose.ui.text.TextStyle(
                        fontSize = 42.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    ),
                    decorationBox = { innerTextField ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Box(modifier = Modifier.weight(1f)) {
                                if (cantidad.isEmpty()) {
                                    Text("0.00", fontSize = 42.sp, color = Color.LightGray, fontWeight = FontWeight.Bold)
                                }
                                innerTextField()
                            }
                            Text(
                                text = "BTC",
                                fontSize = 24.sp,
                                color = Color(0xFF2C2C2C),
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                        }
                    }
                )

                Spacer(modifier = Modifier.height(18.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color(0xFFDADADA))
                )

                Spacer(modifier = Modifier.height(18.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = "Recibirás aproximadamente:",
                        color = Color.Gray,
                        fontSize = 18.sp
                    )

                    val cantNum = cantidad.toDoubleOrNull() ?: 0.0
                    Text(
                        text = String.format(Locale.US, "%.2f %s", cantNum * btcPrice, currency.symbol),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )

                }

                Spacer(modifier = Modifier.height(28.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    BotonPorcentaje("25%") { cantidad = String.format(Locale.US, "%.5f", 1.42857 * 0.25) }

                    BotonPorcentaje("50%") { cantidad = String.format(Locale.US, "%.5f", 1.42857 * 0.50) }

                    BotonPorcentaje("75%") { cantidad = String.format(Locale.US, "%.5f", 1.42857 * 0.75) }

                    BotonPorcentaje("MAX") { cantidad = "1.42857" }

                }

            }

        }

        Spacer(modifier = Modifier.height(20.dp))

        // TARJETAS DE PRECIO Y COMISIÓN
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Card(
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            Icons.Default.TrendingUp,
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier.size(18.dp)
                        )

                        Spacer(modifier = Modifier.width(6.dp))

                        Text(
                            text = "PRECIO",
                            color = Color.Gray,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = String.format(Locale.US, "%.2f %s", btcPrice, currency.symbol),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Card(
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            Icons.Default.AccountBalanceWallet,
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier.size(18.dp)
                        )

                        Spacer(modifier = Modifier.width(6.dp))

                        Text(
                            text = "COMISIÓN",
                            color = Color.Gray,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = "0.5%",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // TARJETA DE TRANSACCIÓN ASEGURADA
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFF1F1F1)
            )
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp),
                contentAlignment = Alignment.Center
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Icon(
                        imageVector = Icons.Default.Security,
                        contentDescription = null,
                        tint = Color(0xFF1247E6),
                        modifier = Modifier.size(46.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "TRANSACCIÓN ASEGURADA",
                        fontWeight = FontWeight.Bold,
                        color = Color.DarkGray,
                        fontSize = 17.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        // BOTÓN CONFIRMAR VENTA
        Button(
            onClick = { 
                val cantNum = cantidad.toDoubleOrNull() ?: 0.0
                if (cantNum > 0) {
                    vm.sellBitcoin(cantNum)
                    navController.popBackStack()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1147D9)
            ),
            shape = RoundedCornerShape(14.dp)
        ) {

            Text(
                text = "Confirmar Venta",
                fontSize = 24.sp,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Los fondos se acreditarán en tu cuenta bancaria vinculada en un plazo de 1 a 3 días hábiles.",
            textAlign = TextAlign.Center,
            color = Color.Gray,
            fontSize = 16.sp,
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        Spacer(modifier = Modifier.height(100.dp))
    }
}

@Composable
fun BotonPorcentaje(
    texto: String,
    onClick: () -> Unit = {}
) {

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            Color(0xFFD0D5E5)
        ),
        shape = RoundedCornerShape(50)
    ) {

        Text(
            text = texto,
            fontWeight = FontWeight.Bold
        )

    }

}
