package com.example.demo.data.repository.user

import com.example.demo.data.model.UserDTO
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<UserDTO, Long>{
    fun findByUserId(userId: String): Optional<UserDTO>
}