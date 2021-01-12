package com.fxn.bubbletabbarapp.ui.samples

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.fxn.bubbletabbarapp.R
import com.fxn.bubbletabbarapp.utils.onNavDestinationSelected
import kotlinx.android.synthetic.main.fragment_view_pager2.view.*

class NavControllerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_nav_controller, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var navController = Navigation.findNavController(requireActivity(), R.id.inner_host_nav)
        view.bubbleTabBar.addBubbleListener { id ->
            view.bubbleTabBar.onNavDestinationSelected(id, navController)
        }
        navController.addOnDestinationChangedListener { _, destination, _ ->
            view.bubbleTabBar.setSelectedWithId(destination.id, false)
        }
    }


}