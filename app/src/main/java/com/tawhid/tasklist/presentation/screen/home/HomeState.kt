package com.tawhid.tasklist.presentation.screen.home

import com.tawhid.tasklist.domain.model.TaskModel

data class HomeState(
    val tasks: List<TaskModel> = emptyList(),
    val favoriteTasks: List<TaskModel> = emptyList(),
    val isLoading: Boolean = false,
)