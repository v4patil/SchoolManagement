package com.vibhorpatil.schoolmanagement.presentation.student

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.vibhorpatil.schoolmanagement.presentation.navigation.Screen
import com.vibhorpatil.schoolmanagement.presentation.student.component.StudentListItem
import com.vibhorpatil.schoolmanagement.presentation.uiState.UiState

@Composable
fun StudentListScreen(viewModel: StudentListViewModel, navController: NavHostController) {
    val uiState by viewModel.studentListState.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Screen.EntryFormScreen.StudentEntryForm.title + "/-1")
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add Student")
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (val state = uiState) {
                is UiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                is UiState.Success -> {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(state.data) { student ->
                            StudentListItem(
                                student = student,
                                onItemClick = { id ->
                                    navController.navigate(Screen.EntryFormScreen.StudentEntryForm.title + "/$id")
                                }
                            )
                        }
                    }
                }

                is UiState.Error -> {
                    Text(
                        text = state.errorMessage,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}