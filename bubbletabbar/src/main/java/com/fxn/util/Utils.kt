package com.fxn.util

import android.animation.Animator
import android.animation.StateListAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.GradientDrawable
import android.util.TypedValue
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatTextView
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


var DURATION = 350L
var ALPHA = 0.15f
internal fun AppCompatTextView.expand(container: LinearLayoutCompat, iconColor: Int) {
    val bounds = Rect()
    container.setCustomBackground(iconColor, ALPHA)
    paint.apply {

        getTextBounds(text.toString(), 0, text.length, bounds)
        ValueAnimator.ofInt(0, bounds.width() + paddingLeft + 10).apply {
            addUpdateListener {
                if (it.animatedFraction == (0.0f)) {
                    visibility = View.INVISIBLE
                }
                layoutParams.apply {
                    width = it.animatedValue as Int
                }

                if (it.animatedFraction == (1.0f)) {
                    visibility = View.VISIBLE
                }
                requestLayout()
            }
            interpolator = LinearInterpolator()

            duration = DURATION
        }.start()


    }

}


internal fun AppCompatTextView.collapse(
    container: LinearLayoutCompat,
    iconColor: Int
) {
    animate().alpha(0f).apply {
        setUpdateListener {
            layoutParams.apply {
                width = (width - (width * it.animatedFraction)).toInt()
            }
            if (it.animatedFraction == 1.0f) {
                visibility = View.GONE
                alpha = 1.0f
            }
            interpolator = LinearInterpolator()
            duration = DURATION
            container.setCustomBackground(iconColor, ALPHA - (ALPHA * it.animatedFraction))
            requestLayout()
        }
    }.start()

}

internal fun LinearLayoutCompat.setCustomBackground(color: Int, alpha: Float) {
    val containerBackground = GradientDrawable().apply {
        cornerRadius = 100f
        setTint(
            Color.argb(
                (Color.alpha(color) * alpha).toInt(),
                Color.red(color),
                Color.green(color),
                Color.blue(color)
            )
        )
    }
    background = containerBackground
}

fun spToPx(sp: Float, context: Context): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        sp,
        context.resources.displayMetrics
    ).toInt()
}
