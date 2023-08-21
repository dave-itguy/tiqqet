package uk.co.davetheitguy.tiqqet.controllers

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import uk.co.davetheitguy.tiqqet.data.entities.TodoItem
import uk.co.davetheitguy.tiqqet.services.TodoService
import java.net.URI

@RestController
class TodoController(val service: TodoService) {
    @GetMapping("/v1/todo")
    fun getAll() = ResponseEntity.ok(service.findAll())

    @GetMapping("/v1/todo/{id:\\d+}")
    fun getOne(@PathVariable("id") id: Int) = ResponseEntity.of(service.findById(id))

    @GetMapping("/v1/todo")
    fun create(@RequestBody item: TodoItem): ResponseEntity<TodoItem> {
        val response = service.save(item)
        val id = response.id
        return ResponseEntity.created(URI.create("/v1/todo/$id")).body(response)
    }

    @PutMapping("/v1/todo/{id:\\d+}")
    fun update(@PathVariable("id") id: Int, @RequestBody item: TodoItem): ResponseEntity<*> {
        if (id != item.id) return ResponseEntity.badRequest().body(null)
        service.save(item)
        return ResponseEntity.ok(null)
    }

    @DeleteMapping("/v1/todo/{id:\\d+}")
    fun delete(@PathVariable("id") id: Int): ResponseEntity<*> {
        val optionalItem = service.findById(id)
        if (optionalItem.isPresent) {
            service.delete(optionalItem.get())
        }
        return ResponseEntity.ok(null)
    }
}
