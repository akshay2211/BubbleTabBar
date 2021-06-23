package com.fxn.bubbletabbarapp.ui.samples

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fxn.bubbletabbarapp.R
import com.fxn.bubbletabbarapp.databinding.FragmentViewPagerBinding
import com.fxn.bubbletabbarapp.ui.adapter.ViewPagerAdapter
import com.fxn.bubbletabbarapp.utils.setupViewPager

class ViewPagerFragment : Fragment() {
    private var _binding: FragmentViewPagerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewpager.apply {
            setDurationScroll(1000)
            adapter = ViewPagerAdapter(childFragmentManager)

        }
        binding.bubbleTabBar.setupViewPager(binding.viewpager)


        binding.bubbleTabBar.addBubbleListener { id ->
            when (id) {
                R.id.home -> binding.viewpager.currentItem = 0
                R.id.log -> binding.viewpager.currentItem = 1
                R.id.doc -> binding.viewpager.currentItem = 2
                R.id.setting -> binding.viewpager.currentItem = 3
            }
        }
    }
}