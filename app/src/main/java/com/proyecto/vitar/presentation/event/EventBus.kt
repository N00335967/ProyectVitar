package com.proyecto.vitar.presentation.event

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object EventBus {

    private val _events = MutableSharedFlow<UiEvent>(
        extraBufferCapacity = 1
    )

    val events = _events.asSharedFlow()

    suspend fun sendEvent(event: UiEvent) {
        _events.emit(event)
    }
}