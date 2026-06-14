package com.vibhorpatil.schoolmanagement.presentation.navigation

import androidx.annotation.DrawableRes
import com.vibhorpatil.schoolmanagement.R

sealed class Screen(val title: String, val route: SchoolScreenNavigation) {

    sealed class DrawerScreen(
        title: String,
        dRoute: SchoolScreenNavigation.DrawerScreenNavigation,
        @DrawableRes val icon: Int
    ) : Screen(title, dRoute) {

        object StudentList : DrawerScreen(
            "StudentList",
            SchoolScreenNavigation.DrawerScreenNavigation.StudentList,
            R.drawable.icon_students
        )

        object CourseList : DrawerScreen(
            "CourseList",
            SchoolScreenNavigation.DrawerScreenNavigation.CourseList,
            R.drawable.icon_course
        )

        object EnrollmentList : DrawerScreen(
            "EnrollmentList",
            SchoolScreenNavigation.DrawerScreenNavigation.EnrollmentList,
            R.drawable.icon_enroll
        )
    }

    sealed class EntryFormScreen(
        title: String,
        dRoute: SchoolScreenNavigation.EntryFormScreenNavigation
    ) : Screen(title, dRoute) {

        object StudentEntryForm : EntryFormScreen(
            "StudentEntryForm",
            SchoolScreenNavigation.EntryFormScreenNavigation.StudentEntryForm
        )

        object CourseEntryForm : EntryFormScreen(
            "CourseEntryForm",
            SchoolScreenNavigation.EntryFormScreenNavigation.CourseEntryForm
        )

        object Enrollment : EntryFormScreen(
            "Enrollment",
            SchoolScreenNavigation.EntryFormScreenNavigation.Enrollment
        )
    }
}

val screensInDrawer = listOf(
    Screen.DrawerScreen.StudentList,
    Screen.DrawerScreen.CourseList,
    Screen.DrawerScreen.EnrollmentList,
)