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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.vibhorpatil.schoolmanagement.R
import com.vibhorpatil.schoolmanagement.presentation.components.AppTopBar
import com.vibhorpatil.schoolmanagement.presentation.components.ConfirmationDialog
import com.vibhorpatil.schoolmanagement.presentation.components.EmptyView
import com.vibhorpatil.schoolmanagement.presentation.navigation.Screen
import com.vibhorpatil.schoolmanagement.presentation.student.component.StudentListItem
import com.vibhorpatil.schoolmanagement.presentation.uiState.UiState

@Composable
fun StudentListScreen(
    viewModel: StudentListViewModel,
    navController: NavHostController
) {
    val uiState by viewModel.studentListState.collectAsState()

    val currentRoute = navController.currentBackStackEntry?.destination?.route
    val isSelectionMode = currentRoute?.startsWith("select_student") == true || 
                         currentRoute?.contains("select_student") == true
    var isShowDeleteConfirmationDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            AppTopBar(
                title = if (isSelectionMode) "Select Student" else "Students",
                navigationIcon = R.drawable.ic_back_arrow,
                onNavigationClick = {navController.popBackStack()}
            )
        },
        floatingActionButton = {
            if (!isSelectionMode) {
                FloatingActionButton(onClick = {
                    navController.navigate(Screen.EntryFormScreen.StudentEntryForm.title + "/-1")
                }) {
                    Icon(Icons.Default.Add, contentDescription = "Add Student")
                }
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
                    if (state.data.isNotEmpty()) {
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                            items(state.data) { student ->
                                StudentListItem(
                                    student = student,
                                    onItemClick = { id ->
                                        if (isSelectionMode && currentRoute == "select_student_for_enrollment") {
                                            navController.navigate("select_course_for_student/$id")
                                        } else {
                                            navController.navigate(Screen.EntryFormScreen.StudentEntryForm.title + "/$id")
                                        }
                                    }, onPopMenuItemClick = {menuItemId, studentId ->
                                        when (menuItemId) {
                                            1 -> {
                                                navController.navigate(Screen.EntryFormScreen.StudentEntryForm.title + "/$studentId")
                                            }

                                            2 -> {
                                                isShowDeleteConfirmationDialog = true
                                                viewModel.studentId = studentId
                                            }
                                        }
                                    },
                                    isSelectionMode = isSelectionMode
                                )
                            }
                        }
                    } else {
                        EmptyView("No Students added")
                    }
                }

                is UiState.Error -> {
                    Text(
                        text = state.errorMessage,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

            if (isShowDeleteConfirmationDialog) {
                ConfirmationDialog(
                    "Delete", "Are you Sure?",
                    {
                        isShowDeleteConfirmationDialog = false
                        viewModel.deleteStudent()
                    },
                    {
                        isShowDeleteConfirmationDialog = false
                    }
                )
            }
        }
    }
}