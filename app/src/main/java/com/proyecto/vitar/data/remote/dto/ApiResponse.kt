package com.proyecto.vitar.data.remote.dto

data class ApiResponse<T>(
    val success: Boolean,
    val data: T? = null,
    val message: String? = null,
    val error: ApiError? = null
)

data class ApiError(
    val message: String,
    val details: List<ApiErrorDetail>? = null
)

data class ApiErrorDetail(
    val field: String,
    val message: String
)
