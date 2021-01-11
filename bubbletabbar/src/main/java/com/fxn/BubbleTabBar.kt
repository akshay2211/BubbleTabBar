package com.fxn

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.widget.LinearLayout
import androidx.annotation.IdRes
import androidx.annotation.RequiresApi
import com.fxn.bubbletabbar.R
import com.fxn.parser.MenuParser

class BubbleTabBar : LinearLayout {
    private var onBubbleClickListener: OnBubbleClickListener? = null
    private var disabledIconColorparam: Int = Color.GRAY
    private var horizontalPaddingparam: Float = 0F
    private var iconPaddingparam: Float = 0F
    private var verticalPaddingparam: Float = 0F
    private var iconSizeparam: Float = 0F
    private var titleSizeparam: Float = 0F
    private var customFontparam: Int = 0

    init {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER
    }

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    fun addBubbleListener(onBubbleClickListener: OnBubbleClickListener) {
        this.onBubbleClickListener = onBubbleClickListener
    }

    fun setSelected(position: Int, callListener: Boolean = true) {
        var it = (this@BubbleTabBar.getChildAt(position) as Bubble)

        var b = it.id
        if (oldBubble != null && oldBubble!!.id != b) {
            it.isSelected = !it.isSelected
            oldBubble!!.isSelected = false
        }
        oldBubble = it
        if (onBubbleClickListener != null && callListener) {
            onBubbleClickListener!!.onBubbleClick(it.id)
        }
    }

    fun setSelectedWithId(@IdRes id: Int, callListener: Boolean = true) {
        var it = this@BubbleTabBar.findViewById<Bubble>(id)
        var b = it.id
        if (oldBubble != null && oldBubble!!.id != b) {
            it.isSelected = !it.isSelected
            oldBubble!!.isSelected = false
        }
        oldBubble = it
        if (onBubbleClickListener != null && callListener) {
            onBubbleClickListener!!.onBubbleClick(it.id)
        }
    }

    private fun init(
        context: Context,
        attrs: AttributeSet?
    ) {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL
        if (attrs != null) {
            val attributes =
                context.theme.obtainStyledAttributes(attrs, R.styleable.BubbleTabBar, 0, 0)
            try {
                val menuResource =
                    attributes.getResourceId(R.styleable.BubbleTabBar_bubbletab_menuResource, -1)
                disabledIconColorparam = attributes.getColor(
                    R.styleable.BubbleTabBar_bubbletab_disabled_icon_color,
                    Color.GRAY
                )
                customFontparam =
                    attributes.getResourceId(R.styleable.BubbleTabBar_bubbletab_custom_font, 0)

                iconPaddingparam = attributes.getDimension(
                    R.styleable.BubbleTabBar_bubbletab_icon_padding,
                    resources.getDimension(R.dimen.bubble_icon_padding)
                )
                horizontalPaddingparam = attributes.getDimension(
                    R.styleable.BubbleTabBar_bubbletab_horizontal_padding,
                    resources.getDimension(R.dimen.bubble_horizontal_padding)
                )
                verticalPaddingparam = attributes.getDimension(
                    R.styleable.BubbleTabBar_bubbletab_vertical_padding,
                    resources.getDimension(R.dimen.bubble_vertical_padding)
                )
                iconSizeparam = attributes.getDimension(
                    R.styleable.BubbleTabBar_bubbletab_icon_size,
                    resources.getDimension(R.dimen.bubble_icon_size)
                )
                titleSizeparam = attributes.getDimension(
                    R.styleable.BubbleTabBar_bubbletab_title_size,
                    resources.getDimension(R.dimen.bubble_icon_size)
                )
                if (menuResource >= 0) {
                    setMenuResource(menuResource)
                }
            } finally {
                attributes.recycle()
            }


        }
    }


    private var oldBubble: Bubble? = null

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setMenuResource(menuResource: Int) {
        val menu = (MenuParser(context).parse(menuResource))
        removeAllViews()
        Log.e("menu ", "-->" + menu.size)
        menu.forEach { it ->
            if (it.id == 0) {
                throw ExceptionInInitializerError("Id is not added in menu item")
            }
            it.apply {
                it.horizontal_padding = horizontalPaddingparam
                it.vertical_padding = verticalPaddingparam
                it.icon_size = iconSizeparam
                it.icon_padding = iconPaddingparam
                it.custom_font = customFontparam
                it.disabled_icon_color = disabledIconColorparam
                it.title_size = titleSizeparam
            }
            addView(Bubble(context, it).apply {
                if (it.checked) {
                    this.isSelected = true
                    oldBubble = this
                }
                setOnClickListener {
                    var b = it.id
                    if (oldBubble != null && oldBubble!!.id != b) {
                        (it as Bubble).isSelected = !it.isSelected
                        oldBubble!!.isSelected = false
                    }
                    oldBubble = it as Bubble
                    if (onBubbleClickListener != null) {
                        onBubbleClickListener!!.onBubbleClick(it.id)
                    }
                }
            })

        }
        invalidate()
    }
}
