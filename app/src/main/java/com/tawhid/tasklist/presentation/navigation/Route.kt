package com.tawhid.tasklist.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object HomeScreen : Route

    @Serializable
    data object AddTaskScreen : Route

    @Serializable
    data class TaskDetailsScreen(val taskId: Long) : Route
}