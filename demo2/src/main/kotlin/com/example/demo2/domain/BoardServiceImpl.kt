package com.example.demo2.domain

import com.example.demo2.infrastructure.BoardRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class BoardServiceImpl(
    private val boardRepository: BoardRepository
) : BoardService {

    override suspend fun post(title: String, content: String) {
        boardRepository.save(Board(title, content))
    }

    override suspend fun get(id: Long): BoardDto {
        return BoardDto.of(
            boardRepository.findById(id)
                .orElseThrow {
                    NoSuchElementException("없어유")
            }
        )
    }
}