package com.tawhid.tasklist.presentation.screen.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.tawhid.tasklist.domain.model.TaskModel

@Composable
fun FavoriteTaskItem(
    onTaskClick: (taskId: Long) -> Unit,
    backgroundColor: Color,
    favoriteTask: TaskModel
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor.copy(alpha = 0.2f)
        ),
        modifier = Modifier
            .padding(top = 5.dp, bottom = 5.dp, end = 10.dp)
            .width(160.dp)
            .height(120.dp),
        shape = MaterialTheme.shapes.large,
        onClick = {
            onTaskClick(favoriteTask.id)
        }
    ) {
        Column (
            modifier = Modifier.padding(15.dp).fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ){
            Text(
                text = favoriteTask.title,
                style = MaterialTheme.typography.bodySmall,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = favoriteTask.description,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 1,
                overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(20.dp))

            LinearProgressBar(
                progress = 0.8f,
                height = 8.dp,
                cornerRadius = 3.dp,
                progressColor = backgroundColor,
                backgroundColor = Color(0xFFFFFFFF)
            )
        }
    }
}