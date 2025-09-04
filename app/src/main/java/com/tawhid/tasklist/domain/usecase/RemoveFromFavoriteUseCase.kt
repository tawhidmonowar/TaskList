package com.tawhid.tasklist.domain.usecase

import com.tawhid.tasklist.domain.repository.TaskRepository

class RemoveFromFavoriteUseCase(
    private val taskRepository: TaskRepository
) {
    suspend operator fun invoke(taskId: Long) = taskRepository.removeFromFavorite(taskId)
}