package com.fxn.util

import android.os.Bundle
import android.view.Menu
import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.ui.NavigationUI
import com.fxn.BubbleTabBar
import java.lang.ref.WeakReference

/**
 * Adapted with some tweaks by André Silva from Mayokun Adeniyi's class
 * Check It Out -> https://git.io/JUpp9
 */

class NavigationComponentHelper {
    companion object {

        fun setupWithNavController(
            menu: Menu,
            bubbleTabBar: BubbleTabBar,
            navController: NavController
        ) {
            bubbleTabBar.addBubbleListener { _, position ->
                NavigationUI.onNavDestinationSelected(
                    menu.getItem(
                        position
                    ), navController
                )
            }

            val weakReference = WeakReference(bubbleTabBar)

            navController.addOnDestinationChangedListener(object : NavController.OnDestinationChangedListener{
                override fun onDestinationChanged(
                    controller: NavController,
                    destination: NavDestination,
                    arguments: Bundle?
                ) {
                    val view = weakReference.get()

                    if (view == null){
                        navController.removeOnDestinationChangedListener(this)
                        return
                    }

                    for (menuItemIndex in 0 until menu.size()){
                        val menuItem = menu.getItem(menuItemIndex)
                        if (matchDestination(destination, menuItem.itemId)){
                            menuItem.isChecked = true
                        }
                    }
                }

            })

        }

        fun matchDestination(
            destination : NavDestination,
            @IdRes destId : Int
        ) : Boolean {
            var currentDestination : NavDestination? = destination
            while (currentDestination!!.id != destId && currentDestination.parent != null){
                currentDestination = currentDestination.parent
            }
            return currentDestination.id == destId
        }
    }
}