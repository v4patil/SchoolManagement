package com.vibhorpatil.schoolmanagement.presentation.course

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vibhorpatil.schoolmanagement.data.local.mapper.toDomain
import com.vibhorpatil.schoolmanagement.di.ActivityScope
import com.vibhorpatil.schoolmanagement.domain.model.Course
import com.vibhorpatil.schoolmanagement.domain.repositories.CourseRepository
import com.vibhorpatil.schoolmanagement.presentation.uiState.UiState
import com.vibhorpatil.schoolmanagement.utils.DispatcherProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@ActivityScope
class CourseListViewModel @Inject constructor(
    val courseRepository: CourseRepository,
    val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Course>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Course>>> = _uiState

    init {
        getCourses()
    }

    private fun getCourses() {
        viewModelScope.launch(dispatcherProvider.Main) {
            _uiState.value = UiState.Loading
            courseRepository.getAllCourses()
                .map { courseEntities ->
                    courseEntities.map { it.toDomain() }
                }
                .catch { error ->
                    _uiState.value = UiState.Error(error.toString())
                }
                .collect { courses ->
                    _uiState.value = UiState.Success(courses)
                }
        }
    }

}