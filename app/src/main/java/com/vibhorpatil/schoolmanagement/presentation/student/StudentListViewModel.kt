package com.vibhorpatil.schoolmanagement.presentation.student

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vibhorpatil.schoolmanagement.data.local.mapper.toDomain
import com.vibhorpatil.schoolmanagement.di.ActivityScope
import com.vibhorpatil.schoolmanagement.domain.model.Student
import com.vibhorpatil.schoolmanagement.domain.repositories.StudentRepository
import com.vibhorpatil.schoolmanagement.presentation.uiState.UiState
import com.vibhorpatil.schoolmanagement.utils.DispatcherProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@ActivityScope
class StudentListViewModel @Inject constructor(
    private val studentRepository: StudentRepository,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private val _studentListState = MutableStateFlow<UiState<List<Student>>>(UiState.Loading)
    val studentListState: StateFlow<UiState<List<Student>>> = _studentListState

    var studentId = -1L

    init {
        getStudents()
    }

    private fun getStudents() {
        viewModelScope.launch(dispatcherProvider.Main) {
            _studentListState.value = UiState.Loading
            studentRepository.getStudents()
                .flowOn(dispatcherProvider.IO)
                .map { studentEntities ->
                    studentEntities.map { it.toDomain() }
                }
                .catch { e ->
                    _studentListState.value = UiState.Error(e.message ?: "An unknown error occurred")
                }
                .collect { students ->
                    _studentListState.value = UiState.Success(students)
                }
        }
    }

    fun deleteStudent() {
        viewModelScope.launch(dispatcherProvider.IO) {
            if (studentId != -1L) {
                studentRepository.deleteStudentById(studentId)
                studentId = -1
            }
        }
    }
}