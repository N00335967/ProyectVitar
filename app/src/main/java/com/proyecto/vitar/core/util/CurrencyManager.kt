package com.proyecto.vitar.core.util

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

enum class Currency(val code: String, val symbol: String) {
    USD("usd", "$"),
    PEN("pen", "S/"),
    EUR("eur", "€")
}

object CurrencyManager {
    private val _selectedCurrency = MutableStateFlow(Currency.USD)
    val selectedCurrency = _selectedCurrency.asStateFlow()

    fun updateCurrency(newCurrency: Currency) {
        _selectedCurrency.value = newCurrency
    }
}
