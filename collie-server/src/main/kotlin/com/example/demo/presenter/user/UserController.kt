package com.example.demo.presenter.user

import com.example.demo.presenter.CollieResponse
import com.example.demo.domain.service.UserService
import com.example.demo.presenter.AccessUser
import io.swagger.v3.oas.annotations.Operation
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserController constructor(
    private val userService: UserService
) {
    @Operation(summary = "회원가입", description = "회원가입을 할 수 있따!")
    @PostMapping("sign_up")
    fun signUp(
        @RequestBody request: SignUpRequest
    ): CollieResponse<Unit> {
        userService.signUp(
            userId = request.userId,
            username = request.username,
            password = request.password
        ).getOrThrow()
        return CollieResponse.success(data = Unit)
    }

    @PostMapping("login")
    fun login(
        @RequestBody request: LoginRequest
    ): CollieResponse<LoginResult> {
        val tokens = userService.login(
            userId = request.userId,
            password = request.password
        ).getOrThrow()

        return CollieResponse.success(data = tokens.toLoginResult())
    }

    @PostMapping("login_with_token")
    fun loginWithToken(
        @RequestBody request: LoginWithTokenRequest
    ): CollieResponse<LoginResult> {
        val tokens = userService.login(
            refreshToken = request.refreshToken,
        ).getOrThrow()
        return CollieResponse.success(data = tokens.toLoginResult())
    }

    @GetMapping("confirm_user_id")
    fun confirmUserId(
        @RequestBody request: ConfirmIdRequest
    ): CollieResponse<Boolean> {
        val result = userService.findByUserId(request.userId).getOrNull() != null
        return CollieResponse.success(
            data = result
        )
    }

    @DeleteMapping("withdraw")
    fun withdraw(
        @AuthenticationPrincipal accessUser: AccessUser
    ): CollieResponse<Boolean> {
        return CollieResponse.success(data = userService.withdraw(accessUser.userId).getOrNull() != null)
    }

}