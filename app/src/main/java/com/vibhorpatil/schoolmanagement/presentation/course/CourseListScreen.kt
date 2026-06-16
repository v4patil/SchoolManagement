package com.vibhorpatil.schoolmanagement.presentation.course

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.vibhorpatil.schoolmanagement.R
import com.vibhorpatil.schoolmanagement.presentation.components.AppTopBar
import com.vibhorpatil.schoolmanagement.presentation.components.EmptyView
import com.vibhorpatil.schoolmanagement.presentation.course.component.CourseListItem
import com.vibhorpatil.schoolmanagement.presentation.navigation.Screen
import com.vibhorpatil.schoolmanagement.presentation.uiState.UiState

@Composable
fun CourseListScreen(
    viewModel: CourseListViewModel,
    navController: NavHostController
) {

    val uiState by viewModel.uiState.collectAsState()
    val currentRoute = navController.currentBackStackEntry?.destination?.route
    val isSelectionMode = currentRoute?.startsWith("select_course") == true ||
            currentRoute?.contains("select_course") == true

    Scaffold(
        topBar = {
            AppTopBar(
                title = if (isSelectionMode) "Select Course" else "Courses",
                navigationIcon = R.drawable.ic_back_arrow,
                onNavigationClick = { navController.popBackStack() }
            )
        },
        floatingActionButton = {
            if (!isSelectionMode) {
                FloatingActionButton(
                    onClick = {
                        navController.navigate(Screen.EntryFormScreen.CourseEntryForm.title + "/-1")
                    },
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add Course")
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
                            items(state.data) { course ->
                                CourseListItem(course) { courseId ->
                                    if (isSelectionMode) {
                                        navController.navigate("select_student_for_course/$courseId")
                                    } else {
                                        navController.navigate(Screen.EntryFormScreen.CourseEntryForm.title + "/$courseId")
                                    }
                                }
                            }
                        }
                    } else {
                        EmptyView("No Courses Added")
                    }
                }

                is UiState.Error -> {
                    Text(
                        text = state.errorMessage,
                        modifier = Modifier.align(Alignment.Center),
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}