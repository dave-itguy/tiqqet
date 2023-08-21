package uk.co.davetheitguy.tiqqet.services

import uk.co.davetheitguy.tiqqet.data.entities.TodoItem
import java.util.*

interface TodoService {
    fun findAll(): List<TodoItem>
    fun findById(id: Int): Optional<TodoItem>
    fun save(item: TodoItem): TodoItem
    fun delete(item: TodoItem)
}
