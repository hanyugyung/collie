package com.example.demo.data.service.user

import com.example.demo.CollieCodes
import com.example.demo.CollieException
import com.example.demo.auth.JwtConfigProperty
import com.example.demo.auth.JwtManager
import com.example.demo.data.model.UserDTO
import com.example.demo.data.model.toDomainModel
import com.example.demo.data.repository.user.UserRepository
import com.example.demo.domain.model.user.Tokens
import com.example.demo.domain.model.user.User
import com.example.demo.domain.service.UserService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.ZonedDateTime

@Service
class UserServiceImpl constructor(
    private val repository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtManager: JwtManager,
    private val jwtConfigProperty: JwtConfigProperty
) : UserService {
    override fun findByUserId(userId: String): Result<User> = runCatching {
        repository.findByUserId(userId).get().toDomainModel()
    }

    @Transactional
    override fun signUp(
        userId: String,
        username: String,
        password: String
    ): Result<Unit> = runCatching {
        val result = repository.save(
            UserDTO(
                userId = userId,
                username = username,
                encodedPassword = passwordEncoder.encode(password)
            )
        )
    }

    @Transactional
    override fun login(userId: String, password: String): Result<Tokens> = runCatching {
        val user: UserDTO = repository.findByUserId(userId).get()

        if (passwordEncoder.matches(password, user.encodedPassword)) {
            val accessToken = generateAccessToken(
                id = user.id,
                userId = user.userId,
                username = user.username
            )
            val refreshToken = jwtManager.generateToken(
                id = user.id,
                userId = user.userId,
                username = user.username,
                secret = jwtConfigProperty.secret,
                issuer = jwtConfigProperty.issuer,
                duration = jwtConfigProperty.refreshTokenDuration
            )
            Tokens(accessToken, refreshToken)
        } else {
            throw CollieException(codes = CollieCodes.PASSWORD_NOT_MATCHED)
        }
    }

    @Transactional
    override fun login(refreshToken: String): Result<Tokens> = runCatching {
        if (jwtManager.isTokenExpired(jwtConfigProperty.secret, refreshToken).getOrThrow()) {
            throw CollieException(codes = CollieCodes.TOKEN_EXPIRED)
        } else {
            val properties = jwtManager.getProperties(
                secret = jwtConfigProperty.secret,
                token = refreshToken,
            )
            val id = (properties[JwtManager.CLAIM_ID] as? String)?.toLong()
            val userId = properties[JwtManager.CLAIM_USER_ID] as? String
            val username = properties[JwtManager.CLAIM_USERNAME] as? String
            if (id == null) {
                throw CollieException(codes = CollieCodes.TOKEN_NOT_VALIDATED, message = "id가 없음")
            }
            if (userId.isNullOrBlank()) {
                throw CollieException(codes = CollieCodes.TOKEN_NOT_VALIDATED, message = "userId가 없음")
            }
            if (username.isNullOrBlank()) {
                throw CollieException(codes = CollieCodes.TOKEN_NOT_VALIDATED, message = "username이 없음")
            }
            val newAccessToken = generateAccessToken(id = id, userId = userId, username = username)
            Tokens(accessToken = newAccessToken, refreshToken = refreshToken)
        }
    }

    @Transactional
    override fun withdraw(userId: String): Result<Unit> = runCatching {
        val userDTO = repository.findByUserId(userId = userId).get()
        repository.deleteById(userDTO.id)
    }

    private fun generateAccessToken(id: Long, userId: String, username: String): String {
        return jwtManager.generateToken(
            id = id,
            userId = userId,
            username = username,
            secret = jwtConfigProperty.secret,
            issuer = jwtConfigProperty.issuer,
            duration = jwtConfigProperty.accessTokenDuration
        )
    }
}