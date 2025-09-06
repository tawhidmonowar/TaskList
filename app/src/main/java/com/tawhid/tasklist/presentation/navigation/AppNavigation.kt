package com.tawhid.tasklist.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
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
    CompositionLocalProvider(
        LocalNavController provides rootNavController
    ) {
        NavHost(
            navController = rootNavController,
            startDestination = Route.HomeScreen
        ) {
            composable<Route.HomeScreen> {
                HomeScreen()
            }

            composable<Route.AddTaskScreen> {
                AddTaskScreen()
            }

            composable<Route.TaskDetailsScreen> { backStackEntry ->
                DetailScreen()
            }
        }
    }
}

val LocalNavController = compositionLocalOf<NavHostController> { error("NavHostController not found") }
