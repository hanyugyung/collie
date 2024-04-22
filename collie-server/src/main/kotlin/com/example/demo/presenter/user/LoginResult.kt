package com.example.demo.presenter.user

import com.example.demo.domain.model.user.Tokens

data class LoginResult(
    val accessToken:String,
    val refreshToken:String
)

fun Tokens.toLoginResult():LoginResult{
    return LoginResult(accessToken = accessToken, refreshToken = refreshToken)
}