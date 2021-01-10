package com.fxn

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.fxn.parser.MenuItem
import com.fxn.util.collapse
import com.fxn.util.expand
import com.fxn.util.setColorStateListAnimator

class Bubble(context: Context, var item: MenuItem) : FrameLayout(context) {

    private var icon = ImageView(context)
    private var title = TextView(context)
    private var container = LinearLayout(context)

    private val dpAsPixels = item.horizontal_padding.toInt()
    private val dpAsPixelsVertical = item.vertical_padding.toInt()
    private val dpAsPixelsIcons = item.icon_size.toInt()
    private val dpAsicon_padding = item.icon_padding.toInt()

    init {
        layoutParams = LinearLayout.LayoutParams(
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
            orientation = LinearLayout.HORIZONTAL
        }
        icon.apply {
            layoutParams = LayoutParams(dpAsPixelsIcons, dpAsPixelsIcons).apply {
                gravity = Gravity.CENTER_VERTICAL
            }
        }
        title.apply {
            layoutParams =
                LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
                    setPaddingRelative(dpAsicon_padding, 0, 0, 0)
                    Log.e("dpAsicon_padding", "-> $dpAsicon_padding")
                    gravity = Gravity.CENTER_VERTICAL
                    textAlignment = View.TEXT_ALIGNMENT_GRAVITY
                }

            maxLines = 1
            textSize = item.title_size / resources.displayMetrics.scaledDensity
            visibility = View.GONE
            if (item.custom_font != 0) {
                try {
                    typeface = ResourcesCompat.getFont(context, item.custom_font)
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
                unselectedColor = item.disabled_icon_color
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
