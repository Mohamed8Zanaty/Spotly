package com.creator.spotly.auth.errorhandling

data class FieldErrors(
    val email: String? = null,
    val password: String? = null,
    val general: String? = null
)