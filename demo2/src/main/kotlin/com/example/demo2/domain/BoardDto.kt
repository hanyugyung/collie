package com.example.demo2.domain

class BoardDto(
    private val id: Long
    , private val title: String
    , private val content: String
) {

    companion object {
        fun of(board: Board): BoardDto {
            return BoardDto(board.getId()!!, board.getTitle(), board.getContent())
        }
    }

}