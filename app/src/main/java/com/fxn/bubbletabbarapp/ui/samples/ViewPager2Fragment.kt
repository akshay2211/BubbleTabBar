package com.fxn.bubbletabbarapp.ui.samples

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.fxn.bubbletabbarapp.R
import com.fxn.bubbletabbarapp.ui.adapter.ViewPager2Adapter
import kotlinx.android.synthetic.main.fragment_view_pager2.view.*

class ViewPager2Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_view_pager2, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.viewpager2.apply {
            adapter = ViewPager2Adapter()
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    view.bubbleTabBar.setSelected(position)
                }
            })
        }

        view.bubbleTabBar.addBubbleListener { id ->
            when (id) {
                R.id.home -> view.viewpager2.currentItem = 0
                R.id.log -> view.viewpager2.currentItem = 1
                R.id.doc -> view.viewpager2.currentItem = 2
                R.id.setting -> view.viewpager2.currentItem = 3
            }
        }
    }
}