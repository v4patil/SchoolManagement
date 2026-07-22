package com.vibhorpatil.schoolmanagement.presentation.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vibhorpatil.schoolmanagement.R
import com.vibhorpatil.schoolmanagement.presentation.components.AppTopBar
import com.vibhorpatil.schoolmanagement.presentation.dashboard.component.DashboardCardView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashBoardScreen(dashboardViewModel: DashboardViewModel) {
    val uiState by dashboardViewModel.uiState.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AppTopBar(title = "Hello Vibhor")
        DashboardContent(uiState = uiState)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DashboardContent(
    uiState: DashboardUiState
) {
    val dashboardItems = listOf(
        DashboardCardUiModel(
            icon = R.drawable.icon_students,
            title = "Total Students",
            value = uiState.totalStudents.toString()
        ),
        DashboardCardUiModel(
            icon = R.drawable.icon_students,
            title = "Total Courses",
            value = uiState.totalCourses.toString()
        ),
        DashboardCardUiModel(
            icon = R.drawable.icon_students,
            title = "Total Enrollments",
            value = uiState.totalEnrollments.toString()
        ),
        DashboardCardUiModel(
            icon = R.drawable.icon_students,
            title = "Total Revenue",
            value = "₹${uiState.totalRevenue}"
        )
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2)
        ) {
            items(
                items = dashboardItems,
                key = { it.title }
            ) { item ->

                DashboardCardView(
                    item.icon,
                    item.title,
                    item.value
                )
            }
        }
    }
}