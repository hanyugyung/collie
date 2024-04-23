package com.example.demo

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.text.SimpleDateFormat
import java.util.Date

@Configuration
class SwaggerConfig {
    @Bean
    fun openAPI(): OpenAPI = OpenAPI()
            .components(Components())
            .info(apiInfo())
 
    private fun apiInfo() = Info()
            .title("Collie 서버")
            .description("서버 테스트 가즈아~")
            .version("1.0.0 - ${SimpleDateFormat("yy-MM-dd hh:mm:ss").format(Date())}")
}