package com.fxn.bubbletabbarapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.fxn.bubbletabbarapp.R
import com.fxn.bubbletabbarapp.ui.adapter.MenuRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.recyclerView.adapter = MenuRecyclerViewAdapter {
            findNavController().navigate(
                when (it) {
                    0 -> R.id.action_homeFragment_to_viewPagerFragment
                    1 -> R.id.action_homeFragment_to_viewPager2Fragment
                    else -> R.id.action_homeFragment_to_navControllerFragment
                }
            )
        }
    }
}