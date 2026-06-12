package com.vibhorpatil.schoolmanagement

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.vibhorpatil.schoolmanagement.presentation.course.CourseListScreen
import com.vibhorpatil.schoolmanagement.presentation.dashboard.DashBoardScreen
import com.vibhorpatil.schoolmanagement.presentation.dashboard.component.drawer.DashboardDrawerSheet
import com.vibhorpatil.schoolmanagement.presentation.enrollment.EnrollmentScreen
import com.vibhorpatil.schoolmanagement.presentation.navigation.SchoolScreenNavigation
import com.vibhorpatil.schoolmanagement.presentation.navigation.Screen
import com.vibhorpatil.schoolmanagement.presentation.student.StudentListScreen
import com.vibhorpatil.schoolmanagement.ui.theme.SchoolManagementTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SchoolManagementTheme {
                NavigationDrawerView()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavigationDrawerView() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val drawerState = rememberDrawerState(DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DashboardDrawerSheet(currentRoute, drawerState, navController)
        },
        gesturesEnabled = true
    ) {
        Scaffold {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                NavHost(
                    navController = navController,
                    startDestination = SchoolScreenNavigation.DashboardScreenNavigation.DashboardScreen.route
                ) {
                    composable(SchoolScreenNavigation.DashboardScreenNavigation.DashboardScreen.route) {
                        DashBoardScreen()
                    }
                    composable(Screen.DrawerScreen.StudentList.title) {
                        StudentListScreen()
                    }
                    composable(Screen.DrawerScreen.CourseList.title) {
                        CourseListScreen()
                    }
                    composable(Screen.DrawerScreen.EnrollmentList.title) {
                        EnrollmentScreen()
                    }
                }
            }
        }
    }
}