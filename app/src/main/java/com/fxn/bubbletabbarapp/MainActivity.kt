package com.fxn.bubbletabbarapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.fxn.OnBubbleClickListener
import com.fxn.ariana.ArianaBackgroundListener
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
        viewpager.setDurationScroll(1000)
        viewpager.adapter = ViewPagerAdapter(supportFragmentManager).apply {
            list = ArrayList<String>().apply {
                add("Home")
                add("Logger")
                add("Documents")
                add("Settings")
            }
        }
        viewpager.addOnPageChangeListener(
            ArianaBackgroundListener(
                getColors(),
                iv,
                viewpager
            )
        )

    }

    private fun getColors(): IntArray {
        return intArrayOf(
            ContextCompat.getColor(this, R.color.home),
            ContextCompat.getColor(this, R.color.logger),
            ContextCompat.getColor(this, R.color.documents),
            ContextCompat.getColor(this, R.color.settings),
            ContextCompat.getColor(this, R.color.home)
        )
    }
}
