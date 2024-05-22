package uk.co.davetheitguy.tiqqet.controllers.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import uk.co.davetheitguy.tiqqet.dto.TaskDto
import uk.co.davetheitguy.tiqqet.services.TaskService

@RestController
class TaskApiController(private val taskService:TaskService) {
    @GetMapping("/api/v1/tasks")
    fun getTasks(): ResponseEntity<List<TaskDto>> = ResponseEntity.ok(taskService.getAll())

    // todo: Add other methods to API controller and secure!
}