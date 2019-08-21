package com.ismaeldivita.chipnavigation.util

import android.animation.Animator
import android.animation.StateListAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.vectordrawable.graphics.drawable.ArgbEvaluator

const val ICON_STATE_ANIMATOR_DURATION: Long = 350

@SuppressLint("RestrictedApi")
internal fun ImageView.colorAnimator(
    @ColorInt from: Int,
    @ColorInt to: Int,
    durationInMillis: Long
): Animator = ValueAnimator.ofObject(ArgbEvaluator(), from, to).apply {
    duration = durationInMillis
    addUpdateListener { animator ->
        val color = animator.animatedValue as Int
        run { setColorFilter(color) }
    }
}

internal fun ImageView.setColorStateListAnimator(
    @ColorInt color: Int,
    @ColorInt unselectedColor: Int
) {
    val stateList = StateListAnimator().apply {
        addState(
            intArrayOf(android.R.attr.state_selected),
            colorAnimator(unselectedColor, color, ICON_STATE_ANIMATOR_DURATION)
        )
        addState(
            intArrayOf(),
            colorAnimator(color, unselectedColor, ICON_STATE_ANIMATOR_DURATION)
        )
    }

    stateListAnimator = stateList

    // Refresh the drawable state to avoid the unselected animation on view creation
    refreshDrawableState()
}


internal fun LinearLayoutCompat.setCustomBackground(color: Int) {
    val containerBackground = GradientDrawable().apply {
        cornerRadius = 100f
        setTint(
            Color.argb(
                (Color.alpha(color) * 0.15).toInt(),
                Color.red(color),
                Color.green(color),
                Color.blue(color)
            )
        )
    }

    val states = StateListDrawable()
    background = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        states.addState(intArrayOf(android.R.attr.state_selected), containerBackground)
        states.addState(intArrayOf(), ColorDrawable(Color.TRANSPARENT))
        states
        // foreground = unselected
    } else {
        states.addState(intArrayOf(android.R.attr.state_selected), containerBackground)
        states.addState(intArrayOf(), ColorDrawable(Color.GRAY))
        states
    }
}


internal fun <T : ViewGroup.LayoutParams> View.updateLayoutParams(view: T.() -> Unit) {
    val lp: T = (layoutParams as T).apply { view(this) }
    layoutParams = lp
}