package uk.co.davetheitguy.tiqqet.services.implementations

import org.springframework.stereotype.Service
import uk.co.davetheitguy.tiqqet.dto.TaskDto
import uk.co.davetheitguy.tiqqet.entities.Status
import uk.co.davetheitguy.tiqqet.entities.Task
import uk.co.davetheitguy.tiqqet.repositories.TaskRepository
import uk.co.davetheitguy.tiqqet.services.Mapper
import uk.co.davetheitguy.tiqqet.services.TaskService
import java.util.*

@Service
class TaskServiceImpl(
    val taskRepository: TaskRepository,
    val taskMapper: Mapper<Task, TaskDto>,
    val dtoMapper: Mapper<TaskDto, Task>
) : TaskService {
    override fun getAll(): List<TaskDto> = taskRepository.findAll().map { taskMapper.map(it) }

    override fun update(id: Long, taskDto: TaskDto): TaskDto? {
        taskRepository.findById(id).ifPresent {
            it.name = taskDto.name
            it.description = taskDto.description
            it.status = Status(name = taskDto.status)
            taskRepository.save(it)
        }
        return get(id).orElse(null)
    }

    override fun get(id: Long): Optional<TaskDto> {
        val item = taskRepository.findById(id)
        return if (item.isPresent) Optional.of(taskMapper.map(item.get())) else Optional.empty()
    }

    override fun create(taskDto: TaskDto): TaskDto = taskMapper.map(taskRepository.save(dtoMapper.map(taskDto)))
}
