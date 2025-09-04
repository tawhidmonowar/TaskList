package com.tawhid.tasklist.domain.usecase

import com.tawhid.tasklist.domain.repository.TaskRepository

class GetAllTaskUseCase(
    private val taskRepository: TaskRepository
) {
    operator fun invoke() = taskRepository.getAllTasks()
}