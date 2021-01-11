package com.fxn.bubbletabbarapp.ui.samples

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.fxn.ariana.ArianaBackgroundListener
import com.fxn.bubbletabbarapp.R
import com.fxn.bubbletabbarapp.ui.adapter.ViewPagerAdapter
import com.fxn.bubbletabbarapp.utils.getColors
import com.fxn.bubbletabbarapp.utils.setupViewPager
import kotlinx.android.synthetic.main.fragment_view_pager.view.*

class ViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_view_pager, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.viewpager.apply {
            adapter = ViewPagerAdapter(childFragmentManager)
            addOnPageChangeListener(
                ArianaBackgroundListener(
                    requireActivity().getColors(),
                    view.iv,
                    this
                )
            )
        }
        view.bubbleTabBar.setupViewPager(view.viewpager)

        view.bubbleTabBar.addBubbleListener { id ->
            when (id) {
                R.id.home -> view.viewpager.currentItem = 0
                R.id.log -> view.viewpager.currentItem = 1
                R.id.doc -> view.viewpager.currentItem = 2
                R.id.setting -> view.viewpager.currentItem = 3
            }
        }
    }
}