package com.tawhid.tasklist.presentation.screen.add_task.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateTimePicker(
    formattedDate: String,
    formattedTime: String,
    onShowTimePickerDialog: () -> Unit,
    onShowDatePickerDialog: () -> Unit,
    showDatePickerDialog: Boolean,
    showTimePickerDialog: Boolean,
    onDismissDatePicker: () -> Unit,
    onDateSelected: (Long?) -> Unit,
    datePickerState: DatePickerState,
    dismissTimePicker: () -> Unit,
    onTimeSelected: (Int, Int) -> Unit,
    timePickerState: TimePickerState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = formattedTime,
                onValueChange = { },
                readOnly = true,
                label = { Text(text = "Time") },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            onShowTimePickerDialog()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Create,
                            contentDescription = "Select Time"
                        )
                    }
                },
                singleLine = true,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(5.dp))

            OutlinedTextField(
                value = formattedDate,
                onValueChange = { },
                readOnly = true,
                label = { Text(text = "Date") },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            onShowDatePickerDialog()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Select Date"
                        )
                    }
                },
                singleLine = true,
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
    }

    if (showDatePickerDialog) {
        DatePickerDialog(
            onDismissRequest = onDismissDatePicker,
            confirmButton = {
                TextButton(onClick = {
                    onDateSelected(datePickerState.selectedDateMillis)
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = onDismissDatePicker) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    if (showTimePickerDialog) {
        AlertDialog(
            onDismissRequest = dismissTimePicker,
            title = { Text("Select Time") },
            text = {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    TimePicker(state = timePickerState)
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    onTimeSelected(timePickerState.hour, timePickerState.minute)
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = dismissTimePicker) {
                    Text("Cancel")
                }
            }
        )
    }
}