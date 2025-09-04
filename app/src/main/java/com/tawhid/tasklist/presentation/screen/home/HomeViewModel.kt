package com.tawhid.tasklist.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tawhid.tasklist.domain.usecase.DeleteTaskUseCase
import com.tawhid.tasklist.domain.usecase.GetAllTaskUseCase
import com.tawhid.tasklist.domain.usecase.GetFavoriteTaskUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getAllTaskUseCase: GetAllTaskUseCase,
    private val getFavoriteTaskUseCase: GetFavoriteTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.onStart {
        getFavoriteTasks()
        getTasks()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HomeState()
    )

    private fun getFavoriteTasks() {
        viewModelScope.launch {
            getFavoriteTaskUseCase().collect { tasks ->
                _state.update {
                    it.copy(
                        favoriteTasks = tasks
                    )
                }
            }
        }
    }

    private fun getTasks() {
        viewModelScope.launch {
            getAllTaskUseCase().collect { tasks ->
                _state.update {
                    it.copy(
                        tasks = tasks
                    )
                }
            }
        }
    }

    fun onDeleteTask(taskId: Long) {
        viewModelScope.launch {
            deleteTaskUseCase(taskId)
        }
    }
}
