package com.example.demo2.domain

interface BoardService {
    suspend fun post(title: String, content: String)

    suspend fun get(id: Long): BoardDto
}