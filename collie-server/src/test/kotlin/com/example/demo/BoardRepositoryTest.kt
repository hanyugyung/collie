package com.example.demo

import com.example.demo.data.repository.board.BoardRepository
import com.example.demo.data.model.BoardDTO
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BoardRepositoryTest {

	@Autowired
	private lateinit var repository: BoardRepository

	@Test
	fun testJPA() {
		repeat(3){
			val dto = BoardDTO(
				text = "Hello World $it",
			)
			repository.save(dto)
		}


		val boards = repository.findAll()
		boards.forEach {
			println("board -> $it")
		}
	}

}
