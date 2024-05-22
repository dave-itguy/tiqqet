package uk.co.davetheitguy.tiqqet.controllers.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RestController
import uk.co.davetheitguy.tiqqet.dto.TaskDto
import uk.co.davetheitguy.tiqqet.services.TaskService

@RestController
class TaskApiController(private val taskService:TaskService) {
    @GetMapping("/api/v1/task")
    fun getTasks(): ResponseEntity<List<TaskDto>> = ResponseEntity.ok(taskService.getAll())

    @GetMapping("/api/v1/task/{id:\\d+}")
    fun getTask(@PathVariable("id") id: Long): ResponseEntity<TaskDto> = ResponseEntity.of(taskService.get(id))

    @PostMapping("/api/v1/task")
    fun createTask(taskDto: TaskDto): ResponseEntity<TaskDto> = ResponseEntity.ok(taskService.create(taskDto))

    @PutMapping("/api/v1/task/{id:\\d+}")
    fun updateTask(@PathVariable("id") id: Long, taskDto: TaskDto): ResponseEntity<TaskDto> = ResponseEntity.ok(taskService.update(id, taskDto))
}
