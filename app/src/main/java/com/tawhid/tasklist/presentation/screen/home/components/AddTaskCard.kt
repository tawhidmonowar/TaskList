package com.tawhid.tasklist.presentation.screen.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun AddTaskCard(
    onAddNewTaskClick: () -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorScheme.primaryContainer
        ),
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Row(
            modifier = Modifier.padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column (
                modifier = Modifier.weight(1f)
            ){
                Text(
                    text = "Your today's task almost done!",
                    fontWeight = FontWeight.Bold,
                    color = colorScheme.onPrimary,
                    modifier = Modifier.padding(16.dp)
                )
                Button(
                    onClick = {
                        onAddNewTaskClick()
                    },
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorScheme.surfaceContainer,
                        contentColor = colorScheme.onSurface,
                    ),
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Add Task"
                    )
                }
            }
            Column (
                modifier = Modifier.weight(.8f)
            ) {
                CircularProgressBar(
                    progress = 0.85f,
                    backgroundColor = colorScheme.onSurfaceVariant,
                    progressColor = colorScheme.surfaceContainer,
                    textColor = colorScheme.surfaceContainer
                )
            }
        }
    }
}
