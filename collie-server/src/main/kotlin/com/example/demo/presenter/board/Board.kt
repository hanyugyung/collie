package com.example.demo.presenter.board

data class Board(
        val id: Long,
        val text: String,
        val createdAt: Long = System.currentTimeMillis(),
)