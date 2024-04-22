package com.example.demo.data.repository.board

import com.example.demo.data.model.BoardDTO
import com.example.demo.data.model.UserDTO
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface BoardRepository : JpaRepository<BoardDTO, Long>{
    fun findByText(text: String): Optional<BoardDTO>

}