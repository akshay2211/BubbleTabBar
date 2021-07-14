package io.ak1.parser

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.FontRes

/**
 * [MenuItem] to hold navigation tab element
 */
data class MenuItem(
    val id: Int,
    val title: CharSequence,
    @DrawableRes val icon: Int,
    val enabled: Boolean,
    @ColorInt val iconColor: Int,
    val checked: Boolean = false
) {
    @FontRes
    var customFont: Int = 0
    var horizontalPadding: Float = 0f
    var verticalPadding: Float = 0f
    var iconPadding: Float = 0f
    var iconSize: Float = 0f
    var titleSize: Float = 0f
    var cornerRadius: Float = 0f
    var disabledIconColor: Int = Color.GRAY
}