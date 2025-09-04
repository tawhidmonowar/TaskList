package com.tawhid.tasklist.domain.model

data class TaskModel(
    val id: Long,
    val title: String,
    val description: String,
    val isReminderSet: Boolean,
    val isFavorite: Boolean = false,
    val reminderTime: Long?,
    val createdAt: Long?
)