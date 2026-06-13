package com.vibhorpatil.schoolmanagement.presentation.course

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import com.vibhorpatil.schoolmanagement.presentation.components.EmptyView
import com.vibhorpatil.schoolmanagement.presentation.course.component.CourseListItem
import com.vibhorpatil.schoolmanagement.presentation.uiState.UiState

@Composable
fun CourseListScreen(viewModel: CourseListViewModel, navController: NavHostController) {

    val uiState by viewModel.uiState.collectAsState()

    when (val state = uiState) {
        is UiState.Loading -> {
            CircularProgressIndicator()
        }

        is UiState.Success -> {
            if (state.data.isNotEmpty()) {
                LazyColumn {
                    items(state.data) {
                        CourseListItem(it)
                    }
                }
            } else {
                EmptyView("No Courses Added")
            }
        }

        is UiState.Error -> TODO()
    }
}