package com.example.demo

import com.example.demo.presenter.AccessUser
import com.example.demo.presenter.user.*
import org.aspectj.lang.annotation.Before
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

/**
 * UserController는 어떻게 테스트 하는거지? AccessUser 얻어서 회원탈퇴를 테스트 하고 싶다.
 */
@SpringBootTest
class UserControllerTest {

    @Autowired
    lateinit var userController: UserController

    companion object{
        private const val userId = "charlezz"
        private const val password = "1234"
    }

    @Test
    fun `회원가입 테스트`(){
        signUp()
    }

    @Test
    fun `로그인 테스트`(){
        signUp()
        Assertions.assertSame(CollieCodes.SUCCESS.code, userController.login(LoginRequest(userId, password)).code)
    }

    @Test
    fun `회원 탈퇴 테스트`(){
        signUp()
        Assertions.assertSame(CollieCodes.SUCCESS.code, userController.withdraw(accessUser = AccessUser(
            id = 0L,
            userId = "",
            username = "",
            roles = listOf()
        )))
    }

    private fun signUp(){
        val confirmIdRequest = ConfirmIdRequest(userId = userId)
        Assertions.assertSame(false, userController.confirmUserId(confirmIdRequest).data)
        val signUpRequest = SignUpRequest(
            userId = userId,
            username = "soohwan.ok",
            password = password
        )
        userController.signUp(signUpRequest)
        Assertions.assertSame(true, userController.confirmUserId(confirmIdRequest).data)
    }
}