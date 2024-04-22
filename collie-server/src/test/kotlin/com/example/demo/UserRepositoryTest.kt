package com.example.demo

import com.example.demo.data.model.UserDTO
import com.example.demo.data.repository.user.UserRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.ZonedDateTime
import kotlin.jvm.optionals.getOrNull
import kotlin.random.Random

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private lateinit var repository: UserRepository
    @Test
    fun `사용자 삽입 테스트`() {
        val userId = "charles${Random.nextInt(100)}"
        val userDTO = UserDTO(
            userId = userId,
            username = "soohwan.ok",
            encodedPassword = "password1234",
        )
        repository.save(userDTO)

        val insertedUserDTO = repository.findByUserId(userId).get()
        Assertions.assertEquals(insertedUserDTO.userId, userDTO.userId)
        Assertions.assertEquals(insertedUserDTO.username, userDTO.username)
        Assertions.assertEquals(insertedUserDTO.encodedPassword, userDTO.encodedPassword)
    }
}