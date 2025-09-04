package com.tawhid.tasklist.presentation.screen.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tawhid.tasklist.domain.model.TaskModel
import com.tawhid.tasklist.presentation.theme.backgroundColorsPreset

@Composable
fun TaskList(
    onTaskClick: (taskId: Long) -> Unit,
    onDeleteClick: (taskId: Long) -> Unit,
    tasks: List<TaskModel> = emptyList()
) {

    Row(
        modifier = Modifier.padding(top = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "All Task",
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
                text = tasks.size.toString(),
                modifier = Modifier.align(Alignment.Center),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold
            )
        }
    }

    LazyColumn {
        items(
            count = tasks.size,
            key = { tasks[it].createdAt!! }
        ) { index ->

            val colorIndex = index % backgroundColorsPreset.size
            val backgroundColor = backgroundColorsPreset[colorIndex]

            TaskItem(
                task = tasks[index],
                onTaskClick = {
                    onTaskClick(tasks[index].id)
                },
                onDeleteClick = {
                    onDeleteClick(tasks[index].id)
                },
                backgroundColor = backgroundColor
            )
        }
    }
}