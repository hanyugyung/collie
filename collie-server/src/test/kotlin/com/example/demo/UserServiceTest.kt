package com.example.demo

import com.example.demo.domain.service.UserService
import io.jsonwebtoken.lang.Assert
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
class UserServiceTest {

    @Autowired
    lateinit var userService: UserService

    @Test
    @Transactional
    fun `로그인 테스트`(){
        Assert.notNull(userService)
        val userId = "Charles1234"
        val username = "soohwan ok"
        val password = "1234"
        userService.signUp(
            userId = userId,
            username = username,
            password = password
        ).getOrThrow()

        val tokens = userService.login(
            userId = userId,
            password = password
        ).getOrThrow()

        println("accessToken = ${tokens.accessToken}")
        println("refreshToken = ${tokens.refreshToken}")

        Thread.sleep(1000)
        val tokens2 = userService.login(refreshToken = tokens.refreshToken).getOrThrow()

        println("accessToken2 = ${tokens2.accessToken}")
        println("refreshToken2 = ${tokens2.refreshToken}")

        Assertions.assertEquals(tokens.refreshToken, tokens2.refreshToken)
        Assertions.assertNotEquals(tokens.accessToken, tokens2.accessToken)
    }

    @Test
    @Transactional
    fun `탈퇴 테스트`() {
        Assert.notNull(userService)
        val userId = "Charles1234"
        val username = "soohwan ok"
        val password = "1234"

        userService.signUp(
            userId = userId,
            username = username,
            password = password
        ).getOrThrow()

        val user = userService.findByUserId(userId).getOrThrow()
        Assertions.assertEquals(userId, user.userId)
        Assertions.assertEquals(username, user.username)
        userService.withdraw(userId)
        Assertions.assertNull(userService.findByUserId(userId).getOrNull())

    }
}