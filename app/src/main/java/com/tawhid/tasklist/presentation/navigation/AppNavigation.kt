package com.tawhid.tasklist.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tawhid.tasklist.presentation.screen.add_task.AddTaskScreen
import com.tawhid.tasklist.presentation.screen.detail.DetailScreen
import com.tawhid.tasklist.presentation.screen.home.HomeScreen

@Composable
fun AppNavigation(
    rootNavController: NavHostController
) {
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