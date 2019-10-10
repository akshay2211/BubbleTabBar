package com.fxn.bubbletabbarapp.exampleviewpager2

import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.fxn.OnBubbleClickListener
import com.fxn.bubbletabbarapp.R
import kotlinx.android.synthetic.main.activity_example_viewpager2.*


class ExampleViewPager2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example_viewpager2)

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val height = displayMetrics.heightPixels
        val width = displayMetrics.widthPixels
        Log.e("height", "-> $height")
        Log.e("width", "-> $width")
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        bubbleTabBar.addBubbleListener(object : OnBubbleClickListener {
            override fun onBubbleClick(id: Int) {
                when (id) {
                    R.id.home -> viewpager.currentItem = 0
                    R.id.log -> viewpager.currentItem = 1
                    R.id.doc -> viewpager.currentItem = 2
                    R.id.setting -> viewpager.currentItem = 3
                }
            }
        })
        bubbleTabBar.setupBubbleTabBar(viewpager)
        viewpager.scrollBarFadeDuration = 1000
        viewpager.adapter = PagerAdapter2(supportFragmentManager, lifecycle).apply {
            list = ArrayList<String>().apply {
                add("Home")
                add("Logger")
                add("Documents")
                add("Settings")
            }
        }
    }

}