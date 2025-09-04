package com.tawhid.tasklist.presentation.screen.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.tawhid.tasklist.domain.usecase.AddToFavoriteUseCase
import com.tawhid.tasklist.domain.usecase.GetTaskByIDUseCase
import com.tawhid.tasklist.domain.usecase.RemoveFromFavoriteUseCase
import com.tawhid.tasklist.presentation.navigation.Route
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(
    private val getTaskByIDUseCase: GetTaskByIDUseCase,
    private val addToFavoriteUseCase: AddToFavoriteUseCase,
    private val removeFromFavoriteUseCase: RemoveFromFavoriteUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val taskId = savedStateHandle.toRoute<Route.TaskDetailsScreen>().taskId
    private val _state = MutableStateFlow(DetailState())
    var state = _state.onStart {
        getTask(taskId)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = DetailState()
    )

    private fun getTask(taskId: Long) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    task = getTaskByIDUseCase(taskId),
                    isFavorite = getTaskByIDUseCase(taskId)?.isFavorite ?: false
                )
            }
        }
    }

    fun onFavoriteClick() {
        viewModelScope.launch {
            if (_state.value.task?.isFavorite == true) {
                removeFromFavoriteUseCase(_state.value.task!!.id)
                _state.update {
                    it.copy(
                        isFavorite = false
                    )
                }
            } else {
                addToFavoriteUseCase(_state.value.task!!.id)
                _state.update {
                    it.copy(
                        isFavorite = true
                    )
                }
            }
        }
    }
}