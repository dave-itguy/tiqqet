package uk.co.davetheitguy.tiqqet.services.implementations

import org.springframework.stereotype.Service
import uk.co.davetheitguy.tiqqet.data.entities.TodoItem
import uk.co.davetheitguy.tiqqet.repositories.TodoRepository
import uk.co.davetheitguy.tiqqet.services.TodoService
import java.util.*

@Service
class TodoServiceImpl(val todoRepository: TodoRepository) : TodoService {
    override fun findAll(): List<TodoItem> {
        return todoRepository.findAll()
    }

    override fun findById(id: Int): Optional<TodoItem> {
        return todoRepository.findById(id)
    }

    override fun save(item: TodoItem): TodoItem {
        return todoRepository.save(item)
    }

    override fun delete(item: TodoItem) {
        return todoRepository.delete(item)
    }
}
