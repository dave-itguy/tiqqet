package uk.co.davetheitguy.tiqqet.services.implementations

import org.springframework.stereotype.Service
import uk.co.davetheitguy.tiqqet.entities.Task
import uk.co.davetheitguy.tiqqet.dto.TaskDto
import uk.co.davetheitguy.tiqqet.services.Mapper

@Service
class TaskDtoMapper:Mapper<TaskDto, Task> {
    override fun map(from: TaskDto): Task {
        val task = Task()
        task.id = from.id ?: 0
        task.name = from.name
        task.description = from.description
        task.user = from.user
        return task
    }
}