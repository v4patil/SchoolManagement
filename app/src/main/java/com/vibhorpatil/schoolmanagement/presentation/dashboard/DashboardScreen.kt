package com.vibhorpatil.schoolmanagement.presentation.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.vibhorpatil.schoolmanagement.R
import com.vibhorpatil.schoolmanagement.presentation.components.AppTopBar
import com.vibhorpatil.schoolmanagement.presentation.dashboard.component.DashboardCardView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashBoardScreen(){
    Column(modifier = Modifier.fillMaxSize()
    ) {
        AppTopBar(title = "Hello")
            LazyVerticalGrid(
                columns = GridCells.Fixed(2)
            ) {
                items(4) { item ->
                    DashboardCardView(R.drawable.icon_students, "Total Revenue", "16LPA")
                }
            }
    }
}
