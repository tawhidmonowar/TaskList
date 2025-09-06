package com.tawhid.tasklist.presentation.screen.add_task

import java.time.LocalTime

data class AddTaskState(
    val title: String = "",
    val description: String = "",
    val isReminderSet: Boolean = false,
    val reminderTime: Long? = null,
    val isFavorite: Boolean = false,
    val isLoading: Boolean = false,

    val selectedDateMillis: Long? = null,
    val selectedHour: Int = LocalTime.now().hour,
    val selectedMinute: Int = LocalTime.now().minute,
    val showDatePickerDialog: Boolean = false,
    val showTimePickerDialog: Boolean = false,
    val toastMessage: String? = null
)