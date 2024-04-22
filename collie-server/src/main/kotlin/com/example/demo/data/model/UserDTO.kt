package com.example.demo.data.model

import com.example.demo.domain.model.user.User
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.ZonedDateTime

//@Entity(name = "user") //이건 안되고
//@Entity(name = "user_table") // 이건 되네?
@Entity
data class UserDTO(

    @Column(name = "loginId")
    val userId:String,

    @Column
    val username:String,

    @Column
    val encodedPassword:String,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long =0L,

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    val createdAt: ZonedDateTime = ZonedDateTime.now()
)


fun UserDTO.toDomainModel(): User{
    return User(
        userId = userId,
        username = username
    )
}