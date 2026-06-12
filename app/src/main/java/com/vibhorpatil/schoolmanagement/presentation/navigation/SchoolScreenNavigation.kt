package com.vibhorpatil.schoolmanagement.presentation.navigation

sealed class SchoolScreenNavigation(val route: String) {

    sealed class DrawerScreenNavigation(dRoute: String) : SchoolScreenNavigation(dRoute) {
        object StudentList : DrawerScreenNavigation("StudentList")
        object CourseList : DrawerScreenNavigation("CourseList")
        object EnrollmentList : DrawerScreenNavigation("EnrollmentList")
    }

    sealed class DashboardScreenNavigation(dRoute: String) : SchoolScreenNavigation(dRoute) {
        object DashboardScreen : DashboardScreenNavigation("DashboardScreen")

    }

}