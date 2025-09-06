package com.tawhid.tasklist.presentation.screen.add_task

import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

data class AddTaskState(
    val title: String = "",
    val description: String = "",
    val isReminderSet: Boolean = false,
    val reminderTime: Long? = null,
    val isFavorite: Boolean = false,
    val isLoading: Boolean = false,
    val toastMessage: String? = null

)