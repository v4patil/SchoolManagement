package com.vibhorpatil.schoolmanagement.presentation.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vibhorpatil.schoolmanagement.di.ActivityScope
import com.vibhorpatil.schoolmanagement.domain.repositories.CourseRepository
import com.vibhorpatil.schoolmanagement.domain.repositories.EnrollmentRepository
import com.vibhorpatil.schoolmanagement.domain.repositories.StudentRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@ActivityScope
class DashboardViewModel @Inject constructor(
    studentRepository: StudentRepository,
    courseRepository: CourseRepository,
    enrollmentRepository: EnrollmentRepository
) : ViewModel() {

    val uiState: StateFlow<DashboardUiState> = combine(
        studentRepository.observeStudentCount(),
        courseRepository.observeCourseCount(),
        enrollmentRepository.observeEnrollmentCount()
    ) { studentCount, courseCount, enrollmentCount ->

        DashboardUiState(
            totalStudents = studentCount,
            totalCourses = courseCount,
            totalEnrollments = enrollmentCount,
            totalRevenue = 0.0
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = DashboardUiState()
    )
}