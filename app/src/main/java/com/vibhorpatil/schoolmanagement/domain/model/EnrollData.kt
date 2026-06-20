package com.vibhorpatil.schoolmanagement.domain.model

data class EnrollData(
    val id: Long,
    val title: String,
    val subTitle: String,
    val profile: Int,
    val fee: Double,
    val isEnrolled: Boolean,

    )
