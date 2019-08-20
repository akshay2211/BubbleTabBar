package com.fxn

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.LinearLayoutCompat
import com.fxn.bubbletabbar.R
import com.ismaeldivita.chipnavigation.model.MenuParser


class BubbleTabBar : LinearLayoutCompat {
    var bubble_selected_color: Int = Color.DKGRAY
    var bubble_unselected_color: Int = Color.GRAY
    var icon: Int = 0
    var text: String = "hello"

    init {
        orientation = HORIZONTAL
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

    private fun init(
        context: Context,
        attrs: AttributeSet?
    ) {

        setPadding(20, 10, 10, 20)
        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL
        if (attrs != null) {
            val attributes = context.theme.obtainStyledAttributes(attrs, R.styleable.BubbleTabBar, 0, 0)
            try {
                val menuResource = attributes.getResourceId(R.styleable.BubbleTabBar_bubbletab_menuResource, -1)
                bubble_selected_color =
                    attributes.getColor(R.styleable.BubbleTabBar_bubbletab_selected_color, Color.DKGRAY)
                bubble_unselected_color =
                    attributes.getColor(R.styleable.BubbleTabBar_bubbletab_unselected_color, Color.GRAY)
                icon = attributes.getResourceId(R.styleable.BubbleTabBar_bubbletab_icon, 0)
                text = attributes.getString(R.styleable.BubbleTabBar_bubbletab_text) ?: ""
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
                }
            })

        }
        invalidate()
    }
}
