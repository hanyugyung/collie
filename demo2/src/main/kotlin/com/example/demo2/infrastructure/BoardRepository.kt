package com.example.demo2.infrastructure

import com.example.demo2.domain.Board
import org.springframework.data.jpa.repository.JpaRepository

interface BoardRepository: JpaRepository<Board, Long> {
}