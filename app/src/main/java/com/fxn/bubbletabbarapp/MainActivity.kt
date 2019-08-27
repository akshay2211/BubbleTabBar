package com.fxn.bubbletabbarapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.fxn.OnBubbleClickListner
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bubbleTabBar.addBubbLeListner(object : OnBubbleClickListner {
            override fun onBubbleClick(id: Int) {
                when (id) {
                    R.id.home -> Log.e("hello", "Home")
                    R.id.log -> Log.e("hello", "logger")
                    R.id.doc -> Log.e("hello", "documents")
                    R.id.setting -> Log.e("hello", "setting")
                }
            }
        })
    }
}
