package com.example.demo.auth

import com.example.demo.presenter.AccessUser
import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.Claims
import io.jsonwebtoken.impl.DefaultClaims
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class JwtRequestFilter(
    val secret: String,
    val jwtManager: JwtManager
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val tokenBody: Map<String, Any?> = jwtManager.getProperties(secret, request.getHeader("Token"))
        val id = tokenBody[JwtManager.CLAIM_ID] as? Long
        val userId = tokenBody[JwtManager.CLAIM_USER_ID] as? String
        val username = tokenBody[JwtManager.CLAIM_USERNAME] as? String
        val roles = (tokenBody[JwtManager.CLAIM_ROLES] as? String)?.split(", ") ?: emptyList()

        if (tokenBody.keys.isEmpty() || id == null || userId == null || username == null) {
            response.status = HttpStatus.UNAUTHORIZED.value()
            response.contentType = "application/json"
        } else {
            val accessUser = AccessUser(
                id = id,
                userId = userId,
                username = username,
                roles = roles
            )

            SecurityContextHolder.getContext().authentication = UsernamePasswordAuthenticationToken(
                accessUser,
                null,
                accessUser.authorities
            )
        }
        filterChain.doFilter(request, response)
    }
}