package com.tawhid.tasklist.data.local.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val title: String,
    val description: String,
    val isReminderSet: Boolean,
    val isFavorite: Boolean = false,
    val reminderTime: Long?,
    val createdAt: Long? = System.currentTimeMillis()
)