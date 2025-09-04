package com.tawhid.tasklist.domain.usecase

import com.tawhid.tasklist.domain.repository.TaskRepository

class GetTaskByIDUseCase(
    private val taskRepository: TaskRepository
) {
    suspend operator fun invoke(id: Long) = taskRepository.getTaskById(id)
}