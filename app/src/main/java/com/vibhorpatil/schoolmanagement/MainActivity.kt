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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vibhorpatil.schoolmanagement.di.component.DaggerActivityComponent
import com.vibhorpatil.schoolmanagement.presentation.course.CourseEntryFormScreen
import com.vibhorpatil.schoolmanagement.presentation.course.CourseEntryFormViewModel
import com.vibhorpatil.schoolmanagement.presentation.course.CourseListScreen
import com.vibhorpatil.schoolmanagement.presentation.course.CourseListViewModel
import com.vibhorpatil.schoolmanagement.presentation.dashboard.DashBoardScreen
import com.vibhorpatil.schoolmanagement.presentation.dashboard.component.drawer.DashboardDrawerSheet
import com.vibhorpatil.schoolmanagement.presentation.enrollment.EnrollIntoScreen
import com.vibhorpatil.schoolmanagement.presentation.enrollment.EnrollmentScreen
import com.vibhorpatil.schoolmanagement.presentation.enrollment.EnrollmentViewModel
import com.vibhorpatil.schoolmanagement.presentation.navigation.SchoolScreenNavigation
import com.vibhorpatil.schoolmanagement.presentation.navigation.Screen
import com.vibhorpatil.schoolmanagement.presentation.student.StudentEntryFormScreen
import com.vibhorpatil.schoolmanagement.presentation.student.StudentEntryFormViewModel
import com.vibhorpatil.schoolmanagement.presentation.student.StudentListScreen
import com.vibhorpatil.schoolmanagement.presentation.student.StudentListViewModel
import com.vibhorpatil.schoolmanagement.ui.theme.SchoolManagementTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var studentListViewModel: StudentListViewModel

    @Inject
    lateinit var studentEntryFormViewModel: StudentEntryFormViewModel

    @Inject
    lateinit var courseListViewModel: CourseListViewModel

    @Inject
    lateinit var courseEntryFormViewModel: CourseEntryFormViewModel

    @Inject
    lateinit var enrollmentViewModel: EnrollmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerActivityComponent.builder()
            .applicationComponent((application as SchoolManagementApplication).applicationComponent)
            .build()
            .inject(this)

        enableEdgeToEdge()
        setContent {
            SchoolManagementTheme {
                NavigationDrawerView(
                    studentListViewModel,
                    studentEntryFormViewModel,
                    courseListViewModel,
                    courseEntryFormViewModel,
                    enrollmentViewModel
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavigationDrawerView(
    studentListViewModel: StudentListViewModel,
    studentEntryFormViewModel: StudentEntryFormViewModel,
    courseListViewModel: CourseListViewModel,
    courseEntryFormViewModel: CourseEntryFormViewModel,
    enrollmentViewModel: EnrollmentViewModel
) {
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
                        StudentListScreen(studentListViewModel, navController)
                    }
                    composable(Screen.DrawerScreen.CourseList.title) {
                        CourseListScreen(courseListViewModel, navController)
                    }
                    composable(Screen.DrawerScreen.EnrollmentList.title) {
                        EnrollmentScreen(navController)
                    }
                    composable(
                        route = Screen.EntryFormScreen.StudentEntryForm.title + "/{studentId}",
                        arguments = listOf(navArgument("studentId") { type = NavType.LongType })
                    ) { backStackEntry ->
                        val studentId = backStackEntry.arguments?.getLong("studentId") ?: -1L
                        StudentEntryFormScreen(studentEntryFormViewModel, navController, studentId)
                    }
                    composable(
                        route = Screen.EntryFormScreen.CourseEntryForm.title + "/{courseId}",
                        arguments = listOf(navArgument("courseId") {type = NavType.LongType})
                    ){ backStackEntry ->
                        val courseId = backStackEntry.arguments?.getLong("courseId")?: -1L
                        CourseEntryFormScreen(courseEntryFormViewModel, navController, courseId)
                    }

                    composable("select_student_for_enrollment") {
                        StudentListScreen(studentListViewModel, navController)
                    }

                    composable(
                        route = "select_course_for_student/{studentId}",
                        arguments = listOf(navArgument("studentId") { type = NavType.LongType })
                    ) { backStackEntry ->
                        val studentId = backStackEntry.arguments?.getLong("studentId") ?: -1L
                        EnrollIntoScreen(enrollmentViewModel, navController, studentId, true)
                    }

                    composable("select_course_for_enrollment") {
                        CourseListScreen(courseListViewModel, navController)
                    }

                    composable(
                        route = "select_student_for_course/{courseId}",
                        arguments = listOf(navArgument("courseId") { type = NavType.LongType })
                    ) { backStackEntry ->
                        val courseId = backStackEntry.arguments?.getLong("courseId") ?: -1L
                        EnrollIntoScreen(enrollmentViewModel, navController, courseId, false)
                    }
                }
            }
        }
    }
}