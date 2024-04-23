package com.example.demo

class CollieException(
    val codes: CollieCodes,
    message: String? = null,
    cause: Throwable? = null
) : Throwable(message, cause)