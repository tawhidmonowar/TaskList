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

    val selectedDateMillis: Long? = null,
    val selectedHour: Int = LocalTime.now().hour,
    val selectedMinute: Int = LocalTime.now().minute,
    val showDatePickerDialog: Boolean = false,
    val showTimePickerDialog: Boolean = false,

    val toastMessage: String? = null

) {
    val selectedDate: LocalDate?
        get() = selectedDateMillis?.let {
            Instant.ofEpochMilli(it).atZone(ZoneId.systemDefault()).toLocalDate()
        }

    val selectedTime: LocalTime
        get() = LocalTime.of(selectedHour, selectedMinute)

    val formattedDate: String
        get() = selectedDate?.format(DateTimeFormatter.ofPattern("MMM dd, yyyy")) ?: "Not set"

    val formattedTime: String
        get() = selectedTime.format(DateTimeFormatter.ofPattern("hh:mm a")) ?: "Not set"
}