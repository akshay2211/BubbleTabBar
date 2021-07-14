package io.ak1.bubbletabbarapp.utils

import android.content.Context
import android.util.AttributeSet
import android.view.animation.DecelerateInterpolator
import android.widget.Scroller
import androidx.viewpager.widget.ViewPager
import java.io.IOException

/**
 * custom [ViewPager] to set intended duration for scroll
 */
class PresentationViewPager : ViewPager {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    /**
     * [setDurationScroll] set custom scroll duration in milis
     */
    fun setDurationScroll(millis: Int) {
        try {
            val viewpager = ViewPager::class.java
            val scroller = viewpager.getDeclaredField("mScroller")
            scroller.isAccessible = true
            scroller.set(this, OwnScroller(context, millis))
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    inner class OwnScroller(context: Context, private var durationScroll: Int) :
        Scroller(context, DecelerateInterpolator()) {


        override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int, duration: Int) {
            super.startScroll(startX, startY, dx, dy, durationScroll)
        }
    }

    companion object {
        const val PRESENTATION_MODE_SCROLL_DURATION = 1000
    }
}