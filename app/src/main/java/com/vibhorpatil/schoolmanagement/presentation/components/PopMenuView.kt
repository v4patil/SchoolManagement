package com.vibhorpatil.schoolmanagement.presentation.components

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


@Composable
fun PopMenuView(
    expanded: Boolean,
    onDismiss: () -> Unit,
    onItemClick: (Int) -> Unit
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismiss
    ) {
        DropdownMenuItem(
            text = { Text("Edit") },
            onClick = {
                onItemClick(1)
            }
        )

        DropdownMenuItem(
            text = { Text("Delete") },
            onClick = {
                onItemClick(2)
            }
        )
    }
}
