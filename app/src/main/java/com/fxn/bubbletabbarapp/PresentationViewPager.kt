package com.fxn.bubbletabbarapp

import android.content.Context
import android.util.AttributeSet
import android.view.animation.DecelerateInterpolator
import android.widget.Scroller
import androidx.viewpager.widget.ViewPager

class PresentationViewPager : ViewPager {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    fun setDurationScroll(millis: Int) {
        try {
            val viewpager = ViewPager::class.java
            val scroller = viewpager.getDeclaredField("mScroller")
            scroller.isAccessible = true
            scroller.set(this, OwnScroller(context, millis))
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    inner class OwnScroller(context: Context, var durationScroll: Int) :
        Scroller(context, DecelerateInterpolator()) {


        override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int, duration: Int) {
            super.startScroll(startX, startY, dx, dy, durationScroll)
        }
    }

    companion object {

        val DEFAULT_SCROLL_DURATION = 250
        val PRESENTATION_MODE_SCROLL_DURATION = 1000
    }
}