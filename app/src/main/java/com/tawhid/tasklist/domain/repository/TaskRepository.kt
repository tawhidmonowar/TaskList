package com.tawhid.tasklist.domain.repository

import com.tawhid.tasklist.domain.model.TaskModel
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getAllTasks(): Flow<List<TaskModel>>
    fun getFavouriteTasks(): Flow<List<TaskModel>>
    suspend fun getTaskById(id: Long): TaskModel?
    suspend fun addTask(task: TaskModel)
    suspend fun addToFavorite(id: Long)
    suspend fun removeFromFavorite(id: Long)
    suspend fun deleteTask(id: Long)
    suspend fun update(task: TaskModel)
}