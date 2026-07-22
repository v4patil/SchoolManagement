package com.vibhorpatil.schoolmanagement.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ConfirmationDialog(
    title: String,
    message: String,
    onClickConfirmation: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        title = { Text(text = title) },
        text = { Text(text = message) },
        confirmButton = {
            Text(
                text = "Yes",
                modifier = Modifier.clickable(
                    onClick = { onClickConfirmation() }
                )
            )
        },
        dismissButton = {
            Text(
                text = "No", modifier = Modifier.clickable(
                    onClick = { onDismiss() }
                )
            )
        },
        onDismissRequest = { onDismiss() }
    )

}
