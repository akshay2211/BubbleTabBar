package com.fxn

import android.content.Context
import android.graphics.Color
import android.os.Handler
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import com.fxn.bubbletabbar.R
import com.ismaeldivita.chipnavigation.model.MenuItem
import com.ismaeldivita.chipnavigation.util.setColorStateListAnimator
import com.ismaeldivita.chipnavigation.util.setCustomBackground
import com.ismaeldivita.chipnavigation.util.updateLayoutParams


class Bubble(context: Context, item: MenuItem) : LinearLayoutCompat(context) {

    var icon = AppCompatImageView(context)
    var title = AppCompatTextView(context)

    private val scale = resources.displayMetrics.density
    private val dpAsPixels =
        (resources.getDimension(R.dimen.bubble_horizontal_padding)).toInt()// * scale + 0.5f).toInt()
    private val dpAsPixelsVertical =
        (resources.getDimension(R.dimen.bubble_vertical_padding)).toInt()// * scale + 0.5f).toInt()
    private val dpAsPixelsicons = (resources.getDimension(R.dimen.bubble_icon_size)).toInt() //* scale + 0.5f).toInt()

    init {
        layoutParams = LayoutParams(0, LayoutParams.WRAP_CONTENT, 1f)

        setPadding(dpAsPixels, dpAsPixelsVertical, dpAsPixels, dpAsPixelsVertical)

        icon.apply {
            layoutParams = LayoutParams(dpAsPixelsicons, dpAsPixelsicons).apply {
                // setMargins(dpAsPixels, 0, dpAsPixels, 0)
            }
        }
        title.apply {
            layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
                setPadding(dpAsPixelsVertical, 0, 0, 0)
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
                unselectedColor = Color.parseColor("#1b1b1b")
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
            first.duration = 4000L
            first.addTarget(this@Bubble)
            TransitionManager.beginDelayedTransition(this@Bubble, first)
            updateLayoutParams<LinearLayout.LayoutParams> {
                width = LayoutParams.WRAP_CONTENT
                weight = 0F
            }
            title.visibility = View.VISIBLE
            var scalex = title.width
            Handler().postDelayed({
                title.visibility = View.VISIBLE
            }, 250L)

        } else {
            updateLayoutParams<LayoutParams> {
                width = 0
                weight = 1F
            }
            title.visibility = View.GONE
        }
    }

}