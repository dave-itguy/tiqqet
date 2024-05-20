package uk.co.davetheitguy.tiqqet.services.implementations

import org.springframework.stereotype.Service
import uk.co.davetheitguy.tiqqet.dto.TaskDto
import uk.co.davetheitguy.tiqqet.entities.Task
import uk.co.davetheitguy.tiqqet.repositories.TaskRepository
import uk.co.davetheitguy.tiqqet.services.Mapper
import uk.co.davetheitguy.tiqqet.services.TaskService

@Service
class TaskServiceImpl(val taskRepository: TaskRepository, val taskMapper:Mapper<Task,TaskDto>, val dtoMapper:Mapper<TaskDto,Task>): TaskService {
    override fun getAll(): List<TaskDto> {
        return taskRepository.findAll().map { taskMapper.map(it) }
    }
}
