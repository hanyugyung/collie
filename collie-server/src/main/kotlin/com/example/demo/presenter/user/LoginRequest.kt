package com.example.demo.presenter.user

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

data class LoginRequest(
    @Schema(description = "사용자 아이디", example = "test")
    @NotBlank(message = "필수 입력 값")
    val userId:String,

    @Schema(description = "사용자 비밀번호", example = "1234")
    @NotBlank(message = "필수 입력 값")
    val password:String,
)