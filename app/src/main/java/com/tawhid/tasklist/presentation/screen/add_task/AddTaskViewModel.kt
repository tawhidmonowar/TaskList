package com.tawhid.tasklist.presentation.screen.add_task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tawhid.tasklist.domain.model.TaskModel
import com.tawhid.tasklist.domain.usecase.AddTaskUseCase
import com.tawhid.tasklist.domain.usecase.ReminderUpdateUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddTaskViewModel(
    private val addTaskUseCase: AddTaskUseCase,
    private val reminderUpdateUseCase: ReminderUpdateUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(AddTaskState())
    var state = _state.onStart {

    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = AddTaskState()
    )

    fun onReminderChange(isReminderSet: Boolean) {
        _state.update {
            it.copy(
                isReminderSet = isReminderSet
            )
        }
    }

    fun onTitleChange(title: String) {
        _state.update {
            it.copy(
                title = title
            )
        }
    }

    fun onDescriptionChange(description: String) {
        _state.update {
            it.copy(
                description = description
            )
        }
    }

    fun onFavoriteChange() {
        _state.update {
            it.copy(
                isFavorite = !it.isFavorite
            )
        }
    }

    fun onDateSelected(dateMillis: Long?) {
        viewModelScope.launch {
            _state.update { currentState ->
                currentState.copy(selectedDateMillis = dateMillis, showDatePickerDialog = false)
            }
        }
    }

    fun onTimeSelected(hour: Int, minute: Int) {
        viewModelScope.launch {
            _state.update { currentState ->
                currentState.copy(
                    selectedHour = hour,
                    selectedMinute = minute,
                    showTimePickerDialog = false
                )
            }
        }
    }

    fun showDatePicker() {
        viewModelScope.launch {
            _state.update { it.copy(showDatePickerDialog = true) }
        }
    }

    fun dismissDatePicker() {
        viewModelScope.launch {
            _state.update { it.copy(showDatePickerDialog = false) }
        }
    }

    fun showTimePicker() {
        viewModelScope.launch {
            _state.update { it.copy(showTimePickerDialog = true) }
        }
    }

    fun dismissTimePicker() {
        viewModelScope.launch {
            _state.update { it.copy(showTimePickerDialog = false) }
        }
    }

    fun onAddTask() {
        viewModelScope.launch {
            try {
                addTaskUseCase(
                    TaskModel(
                        id = System.currentTimeMillis(),
                        title = state.value.title,
                        description = state.value.description,
                        isReminderSet = state.value.isReminderSet,
                        isFavorite = state.value.isFavorite,
                        reminderTime = state.value.selectedDateMillis,
                        createdAt = System.currentTimeMillis()
                    )
                )

                println("Selected Date: ${state.value.selectedDateMillis} \n " +
                        "Selected Hour: ${state.value.selectedHour} \n " +
                        "Selected Minute: ${state.value.selectedMinute}")

                _state.update {
                    it.copy(
                        title = "",
                        description = "",
                        isReminderSet = false,
                        isFavorite = false,
                        selectedDateMillis = null,
                        selectedHour = 0,
                        selectedMinute = 0,
                        toastMessage = "Task added successfully!"
                    )
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(toastMessage = "Error adding task: ${e.message}")
                }
            }
        }
    }

    fun onToastMessageShown() {
        _state.update {
            it.copy(toastMessage = null)
        }
    }

    fun updateReminder(taskModel: TaskModel) {
        viewModelScope.launch {
            reminderUpdateUseCase(taskModel)
        }
    }
}