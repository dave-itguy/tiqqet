package uk.co.davetheitguy.tiqqet.services

import uk.co.davetheitguy.tiqqet.dto.TaskDto
import java.util.Optional

interface TaskService {
    fun getAll(): List<TaskDto>
    fun get(id:Long):Optional<TaskDto>
    fun create(taskDto: TaskDto):TaskDto
    fun update(id: Long, taskDto: TaskDto): Optional<TaskDto>
}
