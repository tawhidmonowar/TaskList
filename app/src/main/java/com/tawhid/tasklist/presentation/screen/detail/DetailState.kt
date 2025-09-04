package com.tawhid.tasklist.presentation.screen.detail

import com.tawhid.tasklist.domain.model.TaskModel

data class DetailState(
    val task: TaskModel? = null,
    val isFavorite: Boolean = false
)
