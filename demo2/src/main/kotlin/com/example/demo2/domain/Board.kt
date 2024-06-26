package com.example.demo2.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Board() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null

    private lateinit var title: String

    private lateinit var content: String

    fun getId(): Long? {
        return id
    }

    fun getTitle(): String {
        return title
    }

    fun getContent(): String {
        return content
    }

    constructor(title: String, content: String): this() {
        this.title = title
        this.content =  content
    }

}