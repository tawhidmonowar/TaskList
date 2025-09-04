package com.tawhid.tasklist.data.repository

import com.tawhid.tasklist.data.local.database.TaskDao
import com.tawhid.tasklist.data.local.database.TaskEntity
import com.tawhid.tasklist.domain.model.TaskModel
import com.tawhid.tasklist.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskRepositoryImpl(
    private val taskDao: TaskDao
) : TaskRepository {

    override fun getAllTasks(): Flow<List<TaskModel>> {
        return taskDao.getAllTasks().map { tasks ->
            tasks.map { taskEntity ->
                TaskModel(
                    id = taskEntity.id,
                    title = taskEntity.title,
                    description = taskEntity.description,
                    isReminderSet = taskEntity.isReminderSet,
                    isFavorite = taskEntity.isFavorite,
                    reminderTime = taskEntity.reminderTime,
                    createdAt = taskEntity.createdAt
                )
            }
        }
    }

    override fun getFavouriteTasks(): Flow<List<TaskModel>> {
        return taskDao.getFavoriteTasks().map { tasks ->
            tasks.map { taskEntity ->
                TaskModel(
                    id = taskEntity.id,
                    title = taskEntity.title,
                    description = taskEntity.description,
                    isReminderSet = taskEntity.isReminderSet,
                    isFavorite = taskEntity.isFavorite,
                    reminderTime = taskEntity.reminderTime,
                    createdAt = taskEntity.createdAt
                )
            }
        }
    }

    override suspend fun getTaskById(id: Long): TaskModel? {
        return taskDao.getTaskById(id).let { taskEntity ->
            TaskModel(
                id = taskEntity.id,
                title = taskEntity.title,
                description = taskEntity.description,
                isReminderSet = taskEntity.isReminderSet,
                isFavorite = taskEntity.isFavorite,
                reminderTime = taskEntity.reminderTime,
                createdAt = taskEntity.createdAt
            )
        }
    }

    override suspend fun addTask(task: TaskModel) {
        taskDao.upsertTask(
            TaskEntity(
                id = task.id,
                title = task.title,
                description = task.description,
                isReminderSet = task.isReminderSet,
                isFavorite = task.isFavorite,
                reminderTime = task.reminderTime,
                createdAt = task.createdAt
            )
        )
    }

    override suspend fun addToFavorite(id: Long) {
        taskDao.addToFavorite(id)
    }

    override suspend fun removeFromFavorite(id: Long) {
        taskDao.removeFromFavorite(id)
    }

    override suspend fun deleteTask(id: Long) {
        taskDao.deleteTodo(id)
    }
}