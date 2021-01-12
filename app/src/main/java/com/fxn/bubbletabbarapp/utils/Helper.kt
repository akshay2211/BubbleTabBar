package com.fxn.bubbletabbarapp.utils

import androidx.navigation.ActivityNavigator
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.viewpager.widget.ViewPager
import com.fxn.BubbleTabBar
import com.fxn.bubbletabbarapp.R

/**
 * @author : Akshay Sharma
 * @since : 11/01/21, Mon
 * akshay2211.github.io
 **/


fun BubbleTabBar.setupViewPager(viewPager: ViewPager) {
    viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {

        }

        override fun onPageSelected(position: Int) {
            setSelected(position, false)
        }

        override fun onPageScrollStateChanged(state: Int) {
        }
    })
}

fun BubbleTabBar.onNavDestinationSelected(
    itemId: Int,
    navController: NavController
): Boolean {
    val builder = NavOptions.Builder()
        .setLaunchSingleTop(true)
    if (navController.currentDestination!!.parent!!.findNode(itemId) is ActivityNavigator.Destination) {
        builder.setEnterAnim(R.anim.nav_default_enter_anim)
            .setExitAnim(R.anim.nav_default_exit_anim)
            .setPopEnterAnim(R.anim.nav_default_pop_enter_anim)
            .setPopExitAnim(R.anim.nav_default_pop_exit_anim)
    } else {
        builder.setEnterAnim(R.animator.nav_default_enter_anim)
            .setExitAnim(R.animator.nav_default_exit_anim)
            .setPopEnterAnim(R.animator.nav_default_pop_enter_anim)
            .setPopExitAnim(R.animator.nav_default_pop_exit_anim)
    }
    //if (itemId == getChildAt(0).id) {
    //builder.setPopUpTo(findStartDestination(navController.graph)!!.id, true)
    // }
    builder.setPopUpTo(itemId, true)
    val options = builder.build()
    return try {
        //TODO provide proper API instead of using Exceptions as Control-Flow.
        navController.navigate(itemId, null, options)
        true
    } catch (e: IllegalArgumentException) {
        false
    }
}