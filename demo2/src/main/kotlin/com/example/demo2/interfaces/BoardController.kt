package com.example.demo2.interfaces

import com.example.demo2.domain.BoardDto
import com.example.demo2.domain.BoardService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.coroutines.CoroutineContext

@RequestMapping("/api")
@RestController
class BoardController(
    private val boardService: BoardService
) {

    @PostMapping
    suspend fun saveBoard(): String {
        boardService.post("hello world!", "This is THE FIRST Content")
        return "ok"
    }

    @GetMapping("/{id}")
    suspend fun getBoard(@PathVariable id: Long) : ResponseEntity<BoardDto> {
        val result = boardService.get(id)
        return ResponseEntity.ok(result)
    }
}