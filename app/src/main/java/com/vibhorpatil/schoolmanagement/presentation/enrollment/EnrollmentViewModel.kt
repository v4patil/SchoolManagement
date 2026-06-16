package com.vibhorpatil.schoolmanagement.presentation.enrollment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vibhorpatil.schoolmanagement.data.local.database.entity.EnrollmentEntity
import com.vibhorpatil.schoolmanagement.di.ActivityScope
import com.vibhorpatil.schoolmanagement.domain.repositories.EnrollmentRepository
import com.vibhorpatil.schoolmanagement.utils.DispatcherProvider
import kotlinx.coroutines.launch
import javax.inject.Inject

@ActivityScope
class EnrollmentViewModel @Inject constructor(
    private val enrollmentRepository: EnrollmentRepository,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    fun enrollStudentInCourse(studentId: Long, courseId: Long, courseFee: Double) {
        viewModelScope.launch(dispatcherProvider.IO) {
            val enrollment = EnrollmentEntity(
                studentId = studentId,
                courseId = courseId,
                enrollmentDate = System.currentTimeMillis(),
                courseFee = courseFee
            )
            enrollmentRepository.insertEnrollment(enrollment)
        }
    }
}