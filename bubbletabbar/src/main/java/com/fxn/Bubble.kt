package com.fxn

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import com.fxn.parser.MenuItem
import com.fxn.util.collapse
import com.fxn.util.expand
import com.fxn.util.setColorStateListAnimator


class Bubble(context: Context, var item: MenuItem) : FrameLayout(context) {

    private var icon = AppCompatImageView(context)
    private var title = AppCompatTextView(context)
    private var container = LinearLayoutCompat(context)

    private val dpAsPixels = item.horizontal_padding.toInt()
    private val dpAsPixelsVertical = item.vertical_padding.toInt()
    private val dpAsPixelsIcons = item.icon_size.toInt()

    init {
        layoutParams = LinearLayoutCompat.LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT,
            1f
        ).apply {
            gravity = Gravity.CENTER
        }


        container.apply {
            layoutParams =
                LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
                    setPadding(dpAsPixels, dpAsPixelsVertical, dpAsPixels, dpAsPixelsVertical)
                    gravity = Gravity.CENTER
                }
            gravity = Gravity.CENTER
            orientation = LinearLayoutCompat.HORIZONTAL
        }
        icon.apply {
            layoutParams = LayoutParams(dpAsPixelsIcons, dpAsPixelsIcons).apply {
            }
        }
        title.apply {
            layoutParams =
                LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
                    setPadding(dpAsPixelsVertical, 0, 0, 0)
                    gravity = Gravity.CENTER_VERTICAL
                }

            maxLines = 1
            textSize = item.title_size / resources.displayMetrics.scaledDensity
            visibility = View.GONE
            if (item.custom_font.isNotEmpty()) {
                try {
                    typeface = Typeface.createFromAsset(context.assets, item.custom_font)
                } catch (e: Exception) {
                    Log.e("BubbleTabBar", "Could not get typeface: " + e.message)
                }
            }
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

        container.addView(icon)
        container.addView(title)
        addView(container)
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
            title.expand(container, item.iconColor)
        } else {
            title.collapse(container, item.iconColor)
        }
    }

}