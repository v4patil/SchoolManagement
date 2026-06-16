package com.vibhorpatil.schoolmanagement.presentation.enrollment

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun EnrollIntoScreen(
    enrollmentViewModel: EnrollmentViewModel,
    entityId: Long, isStudent: Boolean
) {

    Text(text = if (isStudent) "student" else "course")

}