package com.vibhorpatil.schoolmanagement.presentation.dashboard.component.drawer

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.vibhorpatil.schoolmanagement.presentation.navigation.Screen
import com.vibhorpatil.schoolmanagement.presentation.navigation.screensInDrawer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DashboardDrawerSheet(
    route: String?,
    drawerState: DrawerState,
    navController: NavHostController
) {
    ModalDrawerSheet {
        val scope: CoroutineScope = rememberCoroutineScope()

        Text("Music", modifier = Modifier.padding(16.dp))
        HorizontalDivider()
        screensInDrawer.forEach {
            DrawerItemView(route == it.title, it) {
                scope.launch {
                    drawerState.close()
                }
                when (it) {
                    Screen.DrawerScreen.StudentList -> {
                        navController.navigate(Screen.DrawerScreen.StudentList.title)
                    }

                    Screen.DrawerScreen.CourseList -> {
                        navController.navigate(Screen.DrawerScreen.CourseList.title)
                    }

                    Screen.DrawerScreen.EnrollmentList -> {
                        navController.navigate(Screen.DrawerScreen.EnrollmentList.title)
                    }
                }
            }
        }
    }

}