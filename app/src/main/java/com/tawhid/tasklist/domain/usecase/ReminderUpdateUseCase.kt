package com.tawhid.tasklist.domain.usecase

import com.tawhid.tasklist.domain.model.TaskModel
import com.tawhid.tasklist.domain.repository.TaskRepository

class ReminderUpdateUseCase(private val taskRepository: TaskRepository) {
    suspend operator fun invoke(task: TaskModel) = taskRepository.update(task)
}