package com.example.demo.domain.service

import com.example.demo.data.model.BoardDTO

interface BoardService {
    suspend fun addBoard(board: BoardDTO): BoardDTO

    suspend fun getBoard(id: Long): BoardDTO?

    suspend fun updateBoard(board: BoardDTO): BoardDTO?

    suspend fun deleteBoard(id: Long)

}
