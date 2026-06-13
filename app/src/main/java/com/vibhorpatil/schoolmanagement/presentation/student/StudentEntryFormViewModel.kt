package com.vibhorpatil.schoolmanagement.presentation.student

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vibhorpatil.schoolmanagement.data.local.database.entity.StudentEntity
import com.vibhorpatil.schoolmanagement.data.local.repositories.StudentRepositoryImpl
import com.vibhorpatil.schoolmanagement.di.ActivityScope
import com.vibhorpatil.schoolmanagement.utils.DispatcherProvider
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@ActivityScope
class StudentEntryFormViewModel @Inject constructor(
    private val studentRepository: StudentRepositoryImpl,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    var studentId by mutableStateOf<Long?>(null)
        private set

    var studentCode by mutableStateOf("")
    var fullName by mutableStateOf("")
    var email by mutableStateOf("")
    var mobileNumber by mutableStateOf("")
    var address by mutableStateOf("")
    var isActive by mutableStateOf(true)

    var isEditMode by mutableStateOf(false)
    var isLoading by mutableStateOf(false)

    fun resetForm() {
        studentId = null
        studentCode = ""
        fullName = ""
        email = ""
        mobileNumber = ""
        address = ""
        isActive = true
        isEditMode = false
        isLoading = false
    }

    fun loadStudent(id: Long) {
        if (id <= 0) {
            resetForm()
            return
        }
        studentId = id
        isEditMode = true
        isLoading = true
        viewModelScope.launch(dispatcherProvider.IO) {
            studentRepository.getStudent(id).firstOrNull()?.let { student ->
                studentCode = student.studentCode
                fullName = student.fullName
                email = student.email
                mobileNumber = student.mobileNumber
                address = student.address ?: ""
                isActive = student.isActive
            }
            isLoading = false
        }
    }

    fun saveStudent(onSuccess: () -> Unit) {
        viewModelScope.launch(dispatcherProvider.IO) {
            val student = StudentEntity(
                studentId = studentId ?: 0,
                studentCode = studentCode,
                fullName = fullName,
                email = email,
                mobileNumber = mobileNumber,
                enrollmentDate = System.currentTimeMillis(),
                address = address,
                isActive = isActive
            )

            if (isEditMode) {
                studentRepository.updateStudent(student)
            } else {
                studentRepository.addStudent(student)
            }
            
            launch(dispatcherProvider.Main) {
                onSuccess()
            }
        }
    }
}