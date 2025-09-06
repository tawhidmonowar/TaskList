package com.tawhid.tasklist.presentation.screen.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tawhid.tasklist.presentation.screen.components.CustomAppBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailScreen(
    detailViewModel: DetailViewModel = koinViewModel(),
    onBackClick: () -> Unit
) {
    val state by detailViewModel.state.collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            CustomAppBar(
                onBackClick = onBackClick,
                onFavoriteClick = {
                    detailViewModel.onFavoriteClick()
                },
                isFavorite = state.isFavorite,
                title = "Detail Screen"
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(5.dp)
                .fillMaxSize()
        ) {
            Text(state.task?.title ?: "No Title found!")
            Text(state.task?.description ?: "No Description found!")
        }
    }
}