package com.tawhid.tasklist.presentation.screen.add_task.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ReminderSwitch(
    isReminderSet: Boolean,
    onReminderChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween
    ) {
        Text("Add Reminder")
        Switch(
            checked = isReminderSet,
            onCheckedChange = {
                onReminderChange(it)
            }
        )
    }
}