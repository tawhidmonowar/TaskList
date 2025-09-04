package com.tawhid.tasklist.presentation.screen.add_task

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tawhid.tasklist.presentation.screen.add_task.components.DateTimePicker
import com.tawhid.tasklist.presentation.screen.add_task.components.ReminderSwitch
import com.tawhid.tasklist.presentation.screen.add_task.components.TitleDescriptionField
import com.tawhid.tasklist.presentation.screen.components.CustomAppBar
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(
    addTaskViewModel: AddTaskViewModel = koinViewModel(),
    onBackClick: () -> Unit
) {

    val state by addTaskViewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = state.selectedDateMillis ?: System.currentTimeMillis()
    )

    val timePickerState = rememberTimePickerState(
        initialHour = state.selectedHour,
        initialMinute = state.selectedMinute
    )

    LaunchedEffect(state.toastMessage) {
        state.toastMessage?.let { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            addTaskViewModel.onToastMessageShown()
        }
    }

    Scaffold(
        topBar = {
            CustomAppBar(
                onBackClick = onBackClick,
                onFavoriteClick = {
                    addTaskViewModel.onFavoriteChange()
                },
                title = "Add a new Task",
                isFavorite = state.isFavorite
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .fillMaxSize()
        ) {

            TitleDescriptionField(
                title = state.title,
                onTitleChange = {
                    addTaskViewModel.onTitleChange(it)
                },
                description = state.description,
                onDescriptionChange = {
                    addTaskViewModel.onDescriptionChange(it)
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            ReminderSwitch(
                isReminderSet = state.isReminderSet,
                onReminderChange = {
                    addTaskViewModel.onReminderChange(it)
                }
            )

            Spacer(modifier = Modifier.height(5.dp))

            AnimatedVisibility(
                visible = state.isReminderSet
            ) {
                DateTimePicker(
                    formattedDate = state.formattedDate,
                    formattedTime = state.formattedTime,
                    onShowTimePickerDialog = {
                        addTaskViewModel.showTimePicker()
                    },
                    onShowDatePickerDialog = {
                        addTaskViewModel.showDatePicker()
                    },
                    showDatePickerDialog = state.showDatePickerDialog,
                    showTimePickerDialog = state.showTimePickerDialog,
                    onDismissDatePicker = {
                        addTaskViewModel.dismissDatePicker()
                    },
                    onDateSelected = {
                        addTaskViewModel.onDateSelected(it)
                    },
                    datePickerState = datePickerState,
                    dismissTimePicker = {
                        addTaskViewModel.dismissTimePicker()
                    },
                    onTimeSelected = { hour, minute ->
                        addTaskViewModel.onTimeSelected(hour, minute)
                    },
                    timePickerState = timePickerState
                )
            }


            Spacer(modifier = Modifier.height(5.dp))

            Button(
                onClick = {
                    addTaskViewModel.onAddTask()
                }
            ) {
                Text("Add Task")
            }
        }
    }
}