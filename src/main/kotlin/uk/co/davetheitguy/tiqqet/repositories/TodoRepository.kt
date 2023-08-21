package uk.co.davetheitguy.tiqqet.repositories

import org.springframework.data.jpa.repository.JpaRepository
import uk.co.davetheitguy.tiqqet.data.entities.TodoItem

interface TodoRepository : JpaRepository<TodoItem, Int>
