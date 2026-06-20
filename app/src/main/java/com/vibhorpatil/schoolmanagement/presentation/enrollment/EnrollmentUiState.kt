package com.vibhorpatil.schoolmanagement.presentation.enrollment

import com.vibhorpatil.schoolmanagement.domain.model.EnrollData

data class EnrollmentUiState(
    val allItems: List<EnrollData> = emptyList(),
    val hasPendingChanges: Boolean = false,
    val isLoading: Boolean = false
) {
    val enrolledItems: List<EnrollData>
        get() = allItems.filter { it.isEnrolled }

    val nonEnrolledItems: List<EnrollData>
        get() = allItems.filter { !it.isEnrolled }
}
