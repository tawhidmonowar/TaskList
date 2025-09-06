package com.tawhid.tasklist.presentation.screen.add_task.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TitleDescriptionField(
    title: String,
    onTitleChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit
) {

    Spacer(modifier = Modifier.height(16.dp))

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Color.White,
                shape = RoundedCornerShape(16.dp)
            ), // Added background and shape
        value = title,
        onValueChange = {
            onTitleChange(it)
        },
        label = {
            Text("Title")
        },
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp)
    )

    Spacer(modifier = Modifier.height(16.dp))

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .background(
                Color.White,
                shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp)
            ),
        value = description,
        onValueChange = {
            onDescriptionChange(it)
        },
        label = {
            Text("Description")
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp)
    )
}
