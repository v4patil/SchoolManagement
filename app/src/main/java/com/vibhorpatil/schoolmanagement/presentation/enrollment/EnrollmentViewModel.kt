package com.vibhorpatil.schoolmanagement.presentation.enrollment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vibhorpatil.schoolmanagement.R
import com.vibhorpatil.schoolmanagement.data.local.database.entity.EnrollmentEntity
import com.vibhorpatil.schoolmanagement.data.local.mapper.toDomain
import com.vibhorpatil.schoolmanagement.di.ActivityScope
import com.vibhorpatil.schoolmanagement.domain.model.EnrollData
import com.vibhorpatil.schoolmanagement.domain.model.Student
import com.vibhorpatil.schoolmanagement.domain.repositories.CourseRepository
import com.vibhorpatil.schoolmanagement.domain.repositories.EnrollmentRepository
import com.vibhorpatil.schoolmanagement.domain.repositories.StudentRepository
import com.vibhorpatil.schoolmanagement.presentation.uiState.UiState
import com.vibhorpatil.schoolmanagement.utils.DispatcherProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@ActivityScope
class EnrollmentViewModel @Inject constructor(
    private val studentRepository: StudentRepository,
    private val courseRepository: CourseRepository,
    private val enrollmentRepository: EnrollmentRepository,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private val _studentProfileState = MutableStateFlow<UiState<Student>>(UiState.Loading)
    val studentProfileState = _studentProfileState

    private val _enrollmentUiState = MutableStateFlow(EnrollmentUiState())
    val enrollmentUiState = _enrollmentUiState

    private val pendingEnrollIds = mutableSetOf<Long>()
    private val pendingUnEnrollIds = mutableSetOf<Long>()

    fun fetchStudentData(studentId: Long) {
        viewModelScope.launch(dispatcherProvider.Main) {
            _studentProfileState.value = UiState.Loading
            studentRepository.getStudent(studentId)
                .flowOn(dispatcherProvider.IO)
                .map { it?.toDomain() }
                .catch {
                    _studentProfileState.value = UiState.Error(it.message ?: "")
                }
                .collect {
                    _studentProfileState.value = UiState.Success(it) as UiState<Student>
                }
        }
    }

    fun loadStudentEnrollments(studentId: Long) {
        viewModelScope.launch(dispatcherProvider.IO) {
            val enrolledCourses = enrollmentRepository.getCoursesForStudent(studentId)

            //map will create a list of courseId and toSet() will create a set without duplicate
            val enrolledIds = enrolledCourses.map { it.courseId }.toSet()

            val allCourses = courseRepository.getAllCourses().first()

            val allItems = allCourses.map { course ->
                    EnrollData(
                        id = course.courseId,
                        title = course.courseName,
                        subTitle = course.instructorName,
                        profile = R.drawable.icon_course,
                        fee = course.fees,
                        isEnrolled = course.courseId in enrolledIds
                    )
                }

            _enrollmentUiState.value = EnrollmentUiState(allItems = allItems)
        }
    }

    fun enrollCourse(courseId: Long) {
        pendingEnrollIds.add(courseId)
        pendingUnEnrollIds.remove(courseId)

        _enrollmentUiState.value = _enrollmentUiState.value.copy(
                allItems =
                    _enrollmentUiState.value.allItems.map {
                        if (it.id == courseId)
                            it.copy(isEnrolled = true)
                        else
                            it
                    },
                hasPendingChanges = true
            )
    }

    fun unEnrollCourse(courseId: Long) {
        pendingUnEnrollIds.add(courseId)
        pendingEnrollIds.remove(courseId)

        _enrollmentUiState.value = _enrollmentUiState.value.copy(
                allItems =
                    _enrollmentUiState.value.allItems.map {
                        if (it.id == courseId)
                            it.copy(isEnrolled = false)
                        else
                            it
                    },
                hasPendingChanges = true
            )
    }

    fun saveEnrollmentChanges(studentId: Long) {
        viewModelScope.launch(dispatcherProvider.IO) {
            pendingEnrollIds.forEach { courseId ->
                enrollmentRepository.insertEnrollment(
                    EnrollmentEntity(
                        studentId = studentId,
                        courseId = courseId,
                        enrollmentDate = System.currentTimeMillis(),
                        courseFee = 0.0
                    )
                )
            }

            pendingUnEnrollIds.forEach { courseId ->
                enrollmentRepository.deleteEnrollment(
                    studentId = studentId,
                    courseId = courseId
                )
            }
            pendingEnrollIds.clear()
            pendingUnEnrollIds.clear()

            _enrollmentUiState.value = _enrollmentUiState.value.copy(
                    hasPendingChanges = false
                )
        }
    }
}