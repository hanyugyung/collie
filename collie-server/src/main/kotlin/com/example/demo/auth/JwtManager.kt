package com.example.demo.auth

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.impl.DefaultClaims
import org.springframework.stereotype.Component
import java.util.Date

@Component
class JwtManager {
    companion object {
        const val CLAIM_ID = "id"
        const val CLAIM_USER_ID = "userId"
        const val CLAIM_USERNAME = "username"
        const val CLAIM_ROLES = "roles"
    }

    fun generateToken(
        id:Long,
        userId: String,
        username: String,
        secret: String,
        issuer: String,
        duration: Long,
    ): String {
        val issuedAt = Date()
        val expiredAt = Date(issuedAt.time + duration)
        val claims = DefaultClaims().apply {
            put(CLAIM_ID, "$id")
            put(CLAIM_USER_ID, userId)
            put(CLAIM_USERNAME, username)
            put(CLAIM_ROLES, listOf("user").joinToString())
            setIssuer(issuer)
            setIssuedAt(issuedAt)
            setExpiration(expiredAt)
        }

        return Jwts.builder()
            .setHeaderParam("alg", "HS256")
            .setHeaderParam("typ", "JWT")
            .setClaims(claims)
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact()

    }

    fun isTokenExpired(
        secret: String,
        token: String
    ): Result<Boolean> = runCatching {
        val claims = getDefaultClaims(secret, token)
        val currentAt = Date()
        val expiredAt = claims.expiration
        expiredAt.before(currentAt)
    }

    private fun getDefaultClaims(
        secret: String,
        token: String
    ): DefaultClaims {
        return DefaultClaims(
            Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .body
        )
    }

    fun getProperties(
        secret: String,
        token: String,
    ): Map<String, Any?>{
        return getDefaultClaims(secret, token)
    }


}