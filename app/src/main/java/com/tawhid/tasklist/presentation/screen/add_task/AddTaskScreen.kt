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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.tawhid.tasklist.core.scheduler.setUpAlarmWithNotification
import com.tawhid.tasklist.domain.model.TaskModel
import com.tawhid.tasklist.presentation.navigation.LocalNavController
import com.tawhid.tasklist.presentation.screen.add_task.components.DateTimePickerSheet
import com.tawhid.tasklist.presentation.screen.add_task.components.ReminderSwitch
import com.tawhid.tasklist.presentation.screen.add_task.components.TitleDescriptionField
import com.tawhid.tasklist.presentation.screen.components.CustomAppBar
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(
    addTaskViewModel: AddTaskViewModel = koinViewModel(),
) {

    val state by addTaskViewModel.state.collectAsStateWithLifecycle()
    var showBottomSheet by remember { mutableStateOf(true) }
    val context = LocalContext.current
    val rootNavController = LocalNavController.current

    LaunchedEffect(state.toastMessage) {
        state.toastMessage?.let { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            addTaskViewModel.onToastMessageShown()
        }
    }

    Scaffold(
        topBar = {
            CustomAppBar(
                onBackClick = {
                    rootNavController.navigateUp()
                },
                onFavoriteClick = {
                    addTaskViewModel.onFavoriteChange()
                },
                title = "Add a new Task",
                isFavorite = state.isFavorite
            )
        },
        containerColor = MaterialTheme.colorScheme.surface
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
                if (showBottomSheet) {
                    DateTimePickerSheet(
                        onSchedule = { triggerTime ->
                            addTaskViewModel.onSchedule(triggerTime)
                            showBottomSheet = false
                        },
                        onDismiss = {
                            showBottomSheet = false
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(5.dp))

            Button(
                onClick = {
                    addTaskViewModel.onAddTask()
                    state.reminderTime?.let {
                        setUpAlarmWithNotification(
                            context,
                            task = TaskModel(
                                id = System.currentTimeMillis(),
                                title = state.title,
                                description = state.description,
                                isReminderSet = state.isReminderSet,
                                isFavorite = state.isFavorite,
                                reminderTime = state.reminderTime,
                                createdAt = System.currentTimeMillis()
                            ),
                            reminderTimeMillis = it
                        )
                    }
                },
                shape = MaterialTheme.shapes.medium,
            ) {
                Text("Add Task")
            }
        }
    }
}