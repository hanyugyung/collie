package com.example.demo

enum class CollieCodes(val code:Int) {
    FAILURE(0),
    SUCCESS(1),
    PASSWORD_NOT_MATCHED(1000),
    TOKEN_EXPIRED(1001),
    TOKEN_NOT_VALIDATED(1002)
}