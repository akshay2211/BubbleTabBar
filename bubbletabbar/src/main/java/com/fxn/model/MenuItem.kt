package com.ismaeldivita.chipnavigation.model

import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes

data class MenuItem(
    val id: Int,
    val title: CharSequence,
    @DrawableRes val icon: Int,
    val enabled: Boolean,
    @ColorInt val iconColor: Int,
    val checked: Boolean = false
)