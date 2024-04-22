package com.example.demo.auth

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.annotation.web.configurers.*
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@Configuration
@EnableConfigurationProperties(JwtConfigProperty::class)
@EnableWebSecurity // 이건 뭐지..
class SecurityConfig {

    @Value("\${jwt.secret}")
    private lateinit var secret: String

    @Bean
    fun getPasswordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder() //BCrypt 해싱 함수를 사용
    }

    @Bean
    fun getConfigure(): WebSecurityCustomizer {
        return WebSecurityCustomizer { webSecurity ->
            webSecurity.ignoring().requestMatchers(
                AntPathRequestMatcher("/api-docs/**"),
                AntPathRequestMatcher("/api-docs"),
                AntPathRequestMatcher("/swagger-ui/**"),
                AntPathRequestMatcher("/h2-console/**"),
                AntPathRequestMatcher("/h2-console"),
                AntPathRequestMatcher("/favicon.ico"),
                AntPathRequestMatcher("/api/users/login"),
                AntPathRequestMatcher("/api/users/sign-up"),
                AntPathRequestMatcher("/upload-file/**"),
                AntPathRequestMatcher("/**/upload-file/**")
            )
        }
    }

    @Bean
    @Throws(Exception::class)
    fun getSecurityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        return httpSecurity
            .authorizeHttpRequests { authorizeHttpRequests ->
                authorizeHttpRequests
                    .requestMatchers(
                        AntPathRequestMatcher("/api/boards"),
                        AntPathRequestMatcher("/api/boards/**"),
                        AntPathRequestMatcher("/api/users/my-page")
                    ).hasRole("user")
                    .anyRequest().permitAll()
            }
            .headers { headers ->
                headers.frameOptions { config ->
                    config.disable()
                }
            }
            .formLogin(Customizer.withDefaults<FormLoginConfigurer<HttpSecurity>>())
            .sessionManagement { session: SessionManagementConfigurer<HttpSecurity?> ->
                session.sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS
                )
            }
            .csrf { obj: CsrfConfigurer<HttpSecurity> -> obj.disable() }
            .addFilterBefore(JwtRequestFilter(secret, JwtManager()), UsernamePasswordAuthenticationFilter::class.java)
//            .exceptionHandling { handling: ExceptionHandlingConfigurer<HttpSecurity?> ->
//                handling
//                    .authenticationEntryPoint((AuthenticationEntryPoint { request: HttpServletRequest?, response: HttpServletResponse, authException: AuthenticationException? ->
//                        response.status = HttpStatus.UNAUTHORIZED.value()
//                        response.contentType = "application/json"
//                        ObjectMapper().writeValue(
//                            response.outputStream,
//                            CollieResponse(
//                                CollieCodes.
//                            )CommonResponse.successWithError(CommonResponse.CustomError.USER_FAIL_AUTHORIZATION)
//                        )
//                    }))
//                    .accessDeniedHandler((AccessDeniedHandler { request: HttpServletRequest?, response: HttpServletResponse, accessDeniedException: AccessDeniedException? ->
//                        response.status = HttpStatus.FORBIDDEN.value()
//                        response.contentType = "application/json"
//                        Utils.getObjectMapper().writeValue(
//                            response.outputStream,
//                            CommonResponse.successWithError(CommonResponse.CustomError.USER_FAIL_ACCESS)
//                        )
//                    }))
//            }
            .build()
    }
}