package com.tawhid.tasklist.presentation.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.tawhid.tasklist.presentation.navigation.LocalNavController
import com.tawhid.tasklist.presentation.navigation.Route
import com.tawhid.tasklist.presentation.screen.home.components.AddTaskCard
import com.tawhid.tasklist.presentation.screen.home.components.FavoriteTasks
import com.tawhid.tasklist.presentation.screen.home.components.HomeAppBar
import com.tawhid.tasklist.presentation.screen.home.components.TaskList
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = koinViewModel()
) {
    val state by homeViewModel.state.collectAsStateWithLifecycle()
    val rootNavController = LocalNavController.current

    Scaffold(
        topBar = { HomeAppBar() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            AddTaskCard(
                onAddNewTaskClick = {
                    rootNavController.navigate(Route.AddTaskScreen)
                }
            )
            FavoriteTasks(
                onTaskClick = { taskId ->
                    rootNavController.navigate(Route.TaskDetailsScreen(taskId))
                },
                favoriteTasks = state.favoriteTasks
            )
            TaskList(
                tasks = state.tasks,
                onTaskClick = { taskId ->
                    rootNavController.navigate(Route.TaskDetailsScreen(taskId))
                },
                onDeleteClick = {
                    homeViewModel.onDeleteTask(it)
                }
            )
        }
    }
}