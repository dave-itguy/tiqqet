package uk.co.davetheitguy.tiqqet

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import uk.co.davetheitguy.tiqqet.dto.TaskDto
import uk.co.davetheitguy.tiqqet.entities.Status
import uk.co.davetheitguy.tiqqet.entities.Task
import uk.co.davetheitguy.tiqqet.services.Mapper
import uk.co.davetheitguy.tiqqet.services.implementations.TaskDtoMapper
import uk.co.davetheitguy.tiqqet.services.implementations.TaskMapper

class MapperTests {
    @Test
    fun mapsToTaskDao() {
        val mapper: Mapper<Task, TaskDto> = TaskMapper()
        val taskStatus = Status(
            id = 1,
            name = "New"
        )
        val task = Task(
            id = 1,
            status = taskStatus,
            description = "This is a test task",
            name = "Test task",
            username = "bob"
        )
        val expected = TaskDto(
            user = "bob",
            description = "This is a test task",
            name = "Test task",
            status = "New"
        )
        val result = mapper.map(task)
        Assertions.assertTrue(result == expected)
    }

    @Test
    fun mapsFromTaskDao() {
        val mapper: Mapper<TaskDto, Task> = TaskDtoMapper()
        val dto = TaskDto(
            name = "Test task",
            status = "New",
            description = "This is a test task",
            user = "bob"
        )
        val taskStatus = Status(name = "New")
        val expected = Task(
            status = taskStatus,
            description = "This is a test task",
            username = "bob",
            name = "Test task"
        )
        val result = mapper.map(dto)
        Assertions.assertTrue(result == expected)
    }
}
