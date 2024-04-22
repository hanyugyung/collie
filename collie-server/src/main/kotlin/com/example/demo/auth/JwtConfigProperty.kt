package com.example.demo.auth

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding
import org.springframework.stereotype.Component

@ConfigurationProperties(prefix = "jwt")
data class JwtConfigProperty constructor(
    var secret:String,
    var issuer:String,
    var accessTokenDuration:Long,
    var refreshTokenDuration:Long
)