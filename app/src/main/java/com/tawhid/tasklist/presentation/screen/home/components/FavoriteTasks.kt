package com.tawhid.tasklist.presentation.screen.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tawhid.tasklist.domain.model.TaskModel
import com.tawhid.tasklist.presentation.theme.backgroundColorsPreset

@Composable
fun FavoriteTasks(
    onTaskClick: (taskId: Long) -> Unit,
    favoriteTasks: List<TaskModel> = emptyList()
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Favorite Tasks",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Box(
                modifier = Modifier
                    .size(25.dp)
                    .padding(5.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surfaceContainer)
            ) {
                Text(
                    text = favoriteTasks.size.toString(),
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        LazyRow {
            items(
                count = favoriteTasks.size,
                key = { favoriteTasks[it].createdAt!! }
            ) { index ->

                val colorIndex = index % backgroundColorsPreset.size
                val backgroundColor = backgroundColorsPreset[colorIndex]

                FavoriteTaskItem(
                    favoriteTask = favoriteTasks[index],
                    onTaskClick = onTaskClick,
                    backgroundColor = backgroundColor
                )
            }
        }
    }
}