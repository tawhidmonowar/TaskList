package com.tawhid.tasklist.presentation.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.tawhid.tasklist.presentation.navigation.LocalNavController
import com.tawhid.tasklist.presentation.screen.components.CustomAppBar
import com.tawhid.tasklist.presentation.theme.backgroundColorsPreset
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailScreen(
    detailViewModel: DetailViewModel = koinViewModel(),
) {
    val state by detailViewModel.state.collectAsStateWithLifecycle()
    val rootNavController = LocalNavController.current

    Scaffold(
        topBar = {
            CustomAppBar(
                onBackClick = {
                    rootNavController.navigateUp()
                },
                onFavoriteClick = {
                    detailViewModel.onFavoriteClick()
                },
                isFavorite = state.isFavorite,
                title = "Detail Screen"
            )
        },
        containerColor = MaterialTheme.colorScheme.surface
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(8.dp)
                .fillMaxSize()
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                        .clip(ShapeDefaults.Medium)
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    Text(
                        modifier = Modifier.padding(top = 12.dp, start = 12.dp, end = 12.dp),
                        text = "Task Title",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.primary.copy(
                                alpha = 0.8f
                            )
                        ),
                    )
                    Text(
                        modifier = Modifier.padding(bottom = 12.dp, start = 12.dp, end = 12.dp),
                        text = state.task?.title ?: "No Title found!",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(bottom = 8.dp)
                        .clip(ShapeDefaults.Medium)
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    Text(
                        modifier = Modifier.padding(top = 12.dp, start = 12.dp, end = 12.dp),
                        text = "Description",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.primary.copy(
                                alpha = 0.8f
                            )
                        ),
                    )
                    Text(
                        modifier = Modifier.padding(bottom = 12.dp, start = 12.dp, end = 12.dp),
                        text = state.task?.description ?: "No Description found!",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    }
}