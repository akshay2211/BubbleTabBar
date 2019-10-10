package com.fxn.bubbletabbarapp.exampleviewpager2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.fxn.bubbletabbarapp.ViewPagerAdapter

class PagerAdapter2(
    fm: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fm, lifecycle) {

    var list = ArrayList<String>()

    override fun createFragment(
        position: Int
    ): Fragment = ViewPagerAdapter.Child.newInstance(list[position])

    override fun getItemCount(): Int = list.size

}