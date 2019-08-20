package com.fxn

import android.content.Context
import android.graphics.Color
import android.os.Handler
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import com.ismaeldivita.chipnavigation.model.MenuItem
import com.ismaeldivita.chipnavigation.util.setColorStateListAnimator
import com.ismaeldivita.chipnavigation.util.setCustomBackground
import com.ismaeldivita.chipnavigation.util.updateLayoutParams


class Bubble(context: Context, item: MenuItem) : LinearLayoutCompat(context) {

    var icon = AppCompatImageView(context)
    var title = AppCompatTextView(context)

    private val scale = resources.displayMetrics.density
    private val dpAsPixels = (10 * scale + 0.5f).toInt()
    private val dpAsPixelsVertical = (10 * scale + 0.5f).toInt()
    private val dpAsPixelsicons = (20 * scale + 0.5f).toInt()

    init {
        layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f)
        gravity = Gravity.CENTER_VERTICAL

        setPadding(dpAsPixels, dpAsPixelsVertical, dpAsPixels, dpAsPixelsVertical)
        icon.apply {
            layoutParams = LayoutParams(dpAsPixelsicons, dpAsPixelsicons).apply {
                setMargins(dpAsPixels, 0, dpAsPixels, 0)
            }
            gravity = Gravity.CENTER_VERTICAL
        }
        title.apply {
            layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
                setMargins(0, 0, 10, 0)
            }
            gravity = Gravity.CENTER
            maxLines = 1
            visibility = View.GONE
        }
        id = item.id
        isEnabled = item.enabled
        title.text = item.title
        title.setTextColor(item.iconColor)

        icon.setImageResource(item.icon)
        if (isEnabled) {
            icon.setColorStateListAnimator(
                color = item.iconColor,
                unselectedColor = Color.parseColor("#1b1b1b"),
                disabledColor = Color.parseColor("#ff0000")
            )
        } else {
            icon.setColorFilter(Color.GRAY)
            setOnClickListener(null)
        }

        addView(icon)
        addView(title)
        setCustomBackground(item.iconColor)
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        icon.jumpDrawablesToCurrentState()
        if (!enabled && isSelected) {
            isSelected = false
        }
    }

    override fun setSelected(selected: Boolean) {
        super.setSelected(selected)

        if (selected) {
            val first = ChangeBounds()
            first.duration = 400L
            first.addTarget(this@Bubble)

            TransitionManager.beginDelayedTransition(this@Bubble, first)
            updateLayoutParams<LayoutParams> {
                width = ViewGroup.LayoutParams.WRAP_CONTENT
                weight = 0F
            }
            title.visibility = View.INVISIBLE
            Handler().postDelayed({
                title.visibility = View.VISIBLE
            }, 250L)

        } else {
            updateLayoutParams<LayoutParams> {
                width = ViewGroup.LayoutParams.WRAP_CONTENT
                weight = 1F
            }
            title.visibility = View.GONE
        }
    }

}