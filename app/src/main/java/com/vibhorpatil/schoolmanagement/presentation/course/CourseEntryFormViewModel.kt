package com.vibhorpatil.schoolmanagement.presentation.course

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vibhorpatil.schoolmanagement.data.local.mapper.toDomain
import com.vibhorpatil.schoolmanagement.data.local.mapper.toEntity
import com.vibhorpatil.schoolmanagement.di.ActivityScope
import com.vibhorpatil.schoolmanagement.domain.model.Course
import com.vibhorpatil.schoolmanagement.domain.repositories.CourseRepository
import com.vibhorpatil.schoolmanagement.utils.DispatcherProvider
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@ActivityScope
class CourseEntryFormViewModel @Inject constructor(
    val courseRepository: CourseRepository,
    val dispatcherProvider: DispatcherProvider
) : ViewModel() {
    var isLoading by mutableStateOf(false)
    var isInEditMode by mutableStateOf(false)

    var courseId by mutableStateOf<Long?>(null)
        private set
    var courseName by mutableStateOf("")
    var courseDescription by mutableStateOf("")
    var courseDuration by mutableIntStateOf(0)
    var courseFees by mutableDoubleStateOf(0.0)
    var courseInstructorName by mutableStateOf("")
    var isActive by mutableStateOf(true)


    fun loadCourse(courseId: Long) {
        if (courseId <= 0) {
            resetForm()
            return
        }
        viewModelScope.launch(dispatcherProvider.IO) {
            this@CourseEntryFormViewModel.courseId = courseId
            isLoading = true
            isInEditMode = true
            courseRepository.getCourseById(courseId)?.let { courseEntity ->
                val course = courseEntity.toDomain()
                courseName = course.courseName
                courseDescription = course.courseName
                courseDuration = course.description.toIntOrNull() ?: 0
                courseFees = course.fees
                courseInstructorName = course.courseName
                isActive = course.isActive
            }
            isLoading = false
        }
    }

    private fun resetForm() {
        isLoading = false
        isInEditMode = false

        courseId = null
        courseName = ""
        courseDescription = ""
        courseDuration = 0
        courseFees = 0.0
        courseInstructorName = ""
        isActive = true
    }

    fun saveCourse(onSuccess: () -> Unit) {
        viewModelScope.launch(dispatcherProvider.IO) {
            val course = Course(
                courseId = courseId ?: 0,
                courseCode = "",
                description = courseDescription,
                courseName = courseName,
                durationInMonths = courseDuration,
                fees = courseFees,
                instructorName = courseInstructorName,
                isActive = isActive,
                createdAt = 123L,
                updatedAt = 123L,
            )

            if (isInEditMode) courseRepository.updateCourse(course.toEntity())
            else courseRepository.insertCourse(course.toEntity())

            withContext(dispatcherProvider.Main) {
                onSuccess()
            }
        }
    }
}