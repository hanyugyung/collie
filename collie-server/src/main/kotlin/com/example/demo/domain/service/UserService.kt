package com.example.demo.domain.service

import com.example.demo.domain.model.user.Tokens
import com.example.demo.domain.model.user.User

interface UserService {
    fun findByUserId(userId: String): Result<User>
    fun signUp(
        userId: String,
        username: String,
        password: String
    ): Result<Unit>

    fun login(
        userId: String,
        password: String
    ): Result<Tokens>

    fun login(
        refreshToken: String
    ): Result<Tokens>

    fun withdraw(
        userId: String,
    ): Result<Unit>

}
