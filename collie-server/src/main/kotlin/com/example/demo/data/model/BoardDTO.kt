package com.example.demo.data.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity(name = "board")
data class BoardDTO constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) val id:Long =0L,

    @Column(length = 30) val text:String,

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    val createdAt:LocalDateTime  = LocalDateTime.now()
)