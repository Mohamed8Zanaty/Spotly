package com.creator.spotly.auth.errorhandling

class AuthException(
    val fieldErrors: FieldErrors,
    message: String? = null,
    cause: Throwable? = null
) : Exception(message, cause)