package uk.co.davetheitguy.tiqqet.services.implementations

import org.springframework.stereotype.Service
import uk.co.davetheitguy.tiqqet.dto.TaskDto
import uk.co.davetheitguy.tiqqet.entities.Task
import uk.co.davetheitguy.tiqqet.services.Mapper

@Service
class TaskMapper: Mapper<Task, TaskDto> {
    override fun map(from: Task): TaskDto {
        val taskDto = TaskDto()
        taskDto.name = from.name
        taskDto.description = from.description
        taskDto.status = from.status?.name
        taskDto.user = from.username
        return taskDto
    }
}
