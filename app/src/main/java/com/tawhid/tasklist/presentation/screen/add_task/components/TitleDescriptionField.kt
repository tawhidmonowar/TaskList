package com.tawhid.tasklist.presentation.screen.add_task.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TitleDescriptionField(
    title: String,
    onTitleChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = title,
        onValueChange = {
            onTitleChange(it)
        },
        label = {
            Text("Title")
        },
        singleLine = true
    )
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp),
        value = description,
        onValueChange = {
            onDescriptionChange(it)
        },
        label = {
            Text("Description")
        }
    )
}
