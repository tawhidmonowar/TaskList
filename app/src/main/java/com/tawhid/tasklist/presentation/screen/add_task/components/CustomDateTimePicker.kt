package com.tawhid.tasklist.presentation.screen.add_task.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import java.time.LocalDateTime

@Composable
fun CustomTimePicker(
    modifier: Modifier = Modifier,
    onTimeChange: (LocalDateTime) -> Unit
) {
    val timeInteractionSource = remember { MutableInteractionSource() }

    OutlinedTextField(
        value = "Time",
        onValueChange = {},
        interactionSource = timeInteractionSource,
        label = { Text(text = "Time") },
        supportingText = { Text(text = "hh:mm a") },
        trailingIcon = { Icon(imageVector = Icons.Default.Create, contentDescription = "Time") },
        singleLine = true,
        modifier = modifier
    )
}

@Composable
fun CustomDatePicker(
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    OutlinedTextField(
        value = "Date",
        onValueChange = {},
        interactionSource = interactionSource,
        label = { Text(text = "Date") },
        supportingText = { Text(text = "MM/dd/yyyy") },
        trailingIcon = { Icon(imageVector = Icons.Default.DateRange, contentDescription = "Date") },
        singleLine = true,
        modifier = modifier
    )
}