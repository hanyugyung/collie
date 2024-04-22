package com.example.demo.data.service.board

import com.example.demo.data.repository.board.BoardRepository
import com.example.demo.data.model.BoardDTO
import com.example.demo.domain.service.BoardService
import org.springframework.stereotype.Service

@Service
class BoardServiceImpl(
        private val repository: BoardRepository
): BoardService {

    override suspend fun addBoard(board: BoardDTO): BoardDTO {
        return repository.save(board)

    }

    override suspend fun getBoard(id: Long): BoardDTO? {
        return repository.getReferenceById(id)
    }

    override suspend fun updateBoard(board: BoardDTO): BoardDTO? {
        return repository.saveAndFlush(board)
    }

    override suspend fun deleteBoard(id: Long) {
        return repository.deleteById(id)
    }

}