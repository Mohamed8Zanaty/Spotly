package com.creator.spotly.data.repository

import com.creator.spotly.ui.auth.FieldErrors

class AuthException(
    val fieldErrors: FieldErrors,
    message: String? = null,
    cause: Throwable? = null
) : Exception(message, cause)