package com.tawhid.tasklist.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tawhid.tasklist.presentation.navigation.Route
import com.tawhid.tasklist.presentation.screen.add_task.AddTaskScreen
import com.tawhid.tasklist.presentation.screen.detail.DetailScreen
import com.tawhid.tasklist.presentation.screen.detail.DetailViewModel
import com.tawhid.tasklist.presentation.screen.home.HomeScreen
import com.tawhid.tasklist.presentation.theme.TaskListTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskListTheme {
                val rootNavController = rememberNavController()
                NavHost(
                    navController = rootNavController,
                    startDestination = Route.HomeScreen
                ) {
                    composable<Route.HomeScreen> {
                        HomeScreen(
                            onAddNewTaskClick = {
                                rootNavController.navigate(Route.AddTaskScreen)
                            },
                            onTaskClick = { taskId ->
                                rootNavController.navigate(Route.TaskDetailsScreen(taskId))
                            }

                        )
                    }
                    composable<Route.AddTaskScreen> {
                        AddTaskScreen(
                            onBackClick = {
                                rootNavController.navigateUp()
                            }
                        )
                    }
                    composable<Route.TaskDetailsScreen> { backStackEntry ->
                        DetailScreen(
                            onBackClick = {
                                rootNavController.navigateUp()
                            }
                        )
                    }
                }
            }
        }
    }
}