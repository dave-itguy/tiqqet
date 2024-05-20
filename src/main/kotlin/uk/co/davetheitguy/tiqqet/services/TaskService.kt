package uk.co.davetheitguy.tiqqet.services

import uk.co.davetheitguy.tiqqet.dto.TaskDto

interface TaskService {
    fun getAll(): List<TaskDto>
}