package com.fxn.bubbletabbarapp

import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import com.fxn.OnBubbleClickListener
import com.fxn.ariana.ArianaBackgroundListener
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val height = displayMetrics.heightPixels
        val width = displayMetrics.widthPixels
        Log.e("height", "-> " + height)
        Log.e("width", "-> " + width)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        bubbleTabBar.setupWithNavController(navController)

        /*bubbleTabBar.addBubbleListener(object : OnBubbleClickListener {
            override fun onBubbleClick(id: Int) {
                when (id) {
                    R.id.home -> viewpager.currentItem = 0
                    R.id.log -> viewpager.currentItem = 1
                    R.id.doc -> viewpager.currentItem = 2
                    R.id.setting -> viewpager.currentItem = 3
                }
            }
        })*/

        bubbleTabBar.addBubbleListener { id ->
            when (id) {
                R.id.home -> viewpager.currentItem = 0
                R.id.log -> viewpager.currentItem = 1
                R.id.doc -> viewpager.currentItem = 2
                R.id.setting -> viewpager.currentItem = 3
            }
        }

        bubbleTabBar.setupBubbleTabBar(viewpager)
        viewpager.setDurationScroll(1000)
        // bottom_nav.setupWithNavController(navController)

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
