package com.vibhorpatil.schoolmanagement.presentation.dashboard

import androidx.annotation.DrawableRes

data class DashboardCardUiModel(
    @DrawableRes val icon: Int,
    val title: String,
    val value: String
)
