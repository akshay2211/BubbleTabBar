package io.ak1

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import io.ak1.parser.MenuItem
import io.ak1.util.setColorStateListAnimator
import io.ak1.util.setCustomBackground

@SuppressLint("ViewConstructor")
class Bubble(context: Context, private var item: MenuItem) : FrameLayout(context) {

    private var icon = ImageView(context)
    private var title = TextView(context)
    private var container = LinearLayout(context)

    private val dpAsPixels = item.horizontalPadding.toInt()
    private val dpAsPixelsVertical = item.verticalPadding.toInt()
    private val dpAsPixelsIcons = item.iconSize.toInt()
    private val dpAsIconPadding = item.iconPadding.toInt()

    private var expandAnimator: ValueAnimator? = null
    private val DURATION = 350L
    private val ALPHA = 0.15f

    init {
        id = item.id
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
            setImageResource(item.icon)
            isEnabled = item.enabled
            if (isEnabled) {
                setColorStateListAnimator(
                    color = item.iconColor,
                    unselectedColor = item.disabledIconColor
                )
            } else {
                setColorFilter(Color.GRAY)
                this@Bubble.setOnClickListener(null)
            }
        }
        title.apply {
            layoutParams =
                LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
                    setPaddingRelative(dpAsIconPadding, 0, 0, 0)
                    gravity = Gravity.CENTER_VERTICAL
                    textAlignment = View.TEXT_ALIGNMENT_GRAVITY
                }

            maxLines = 1
            textSize = item.titleSize / resources.displayMetrics.scaledDensity
            visibility = View.GONE
            if (item.customFont != 0) {
                try {
                    typeface = ResourcesCompat.getFont(context, item.customFont)
                } catch (e: Exception) {
                    Log.e("BubbleTabBar", "Could not get typeface: " + e.message)
                }
            }
            text = item.title
            setTextColor(item.iconColor)
        }


        addView(container.apply {
            addView(icon)
            addView(title)
        })
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
        this.clearAnimation()
        expandAnimator?.removeAllUpdateListeners()
        expandAnimator?.cancel()
        if (selected) {
            expand(container, item.iconColor, item.cornerRadius)
        } else {
            collapse(container, item.iconColor, item.cornerRadius)
        }
    }

    fun expand(container: LinearLayout, iconColor: Int, cornerRadius: Float) {
        val bounds = Rect()
        container.setCustomBackground(iconColor, ALPHA, cornerRadius)
        title.apply {
            paint.apply {
                getTextBounds(text.toString(), 0, text.length, bounds)
                expandAnimator = ValueAnimator.ofInt(0, bounds.width() + paddingStart + 10).apply {
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
                }
                expandAnimator?.start()
            }
        }
    }

    fun collapse(container: LinearLayout, iconColor: Int, cornerRadius: Float) {
        title.apply {
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
                    container.setCustomBackground(
                        iconColor,
                        ALPHA - (ALPHA * it.animatedFraction),
                        cornerRadius
                    )
                    requestLayout()
                }
            }.start()
        }
    }

}
