package com.tawhid.tasklist.data.local.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Upsert
    suspend fun upsertTask(taskEntity: TaskEntity)

    @Query("SELECT * FROM TaskEntity ORDER BY createdAt DESC")
    fun getAllTasks(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM TaskEntity WHERE isFavorite = 1")
    fun getFavoriteTasks(): Flow<List<TaskEntity>>

    @Query("UPDATE TaskEntity SET isFavorite = 1 WHERE id = :taskId")
    suspend fun addToFavorite(taskId: Long)

    @Query("UPDATE TaskEntity SET isFavorite = 0 WHERE id = :taskId")
    suspend fun removeFromFavorite(taskId: Long)

    @Query("SELECT * FROM TaskEntity WHERE id = :taskId")
    suspend fun getTaskById(taskId: Long): TaskEntity

    @Query("Delete FROM TaskEntity where id = :id")
    suspend fun deleteTodo(id: Long)
}