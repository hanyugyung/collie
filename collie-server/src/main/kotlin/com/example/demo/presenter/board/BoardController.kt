package com.example.demo.presenter.board

import com.example.demo.presenter.CollieResponse
import com.example.demo.data.model.BoardDTO
import com.example.demo.domain.service.BoardService
import org.springframework.web.bind.annotation.*


@RestController
class BoardController constructor(
    private val service: BoardService
) {

    @PostMapping("/board")
    suspend fun addBoard(
        @RequestBody board: BoardDTO
    ): CollieResponse<BoardDTO?> {
        return CollieResponse(
            message = "추가",
            data = service.addBoard(board),
            code = 200
        )
    }

    @GetMapping("/board/{id}")
    suspend fun getBoard(@PathVariable id: Long): CollieResponse<BoardDTO?> {
        val board = service.getBoard(id)
        return CollieResponse(
            message = if (board != null) "성공" else "실패",
            data = board,
            code = 200
        )
    }

    @PutMapping("/board")
    suspend fun updateBoard(
        @RequestBody board: BoardDTO
    ): CollieResponse<BoardDTO?> {
        return CollieResponse(
            message = "수정",
            data = service.updateBoard(board),
            code = 200
        )
    }

    @DeleteMapping("/board/{id}")
    suspend fun deleteBoard(
        @PathVariable id: Long
    ): CollieResponse<BoardDTO?> {
        return CollieResponse(
            message = "삭제",
            data = null,
            code = 200
        )
    }
}