package io.ak1.bubbletabbarapp.utils

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.preference.PreferenceManager
import io.ak1.bubbletabbarapp.R

/**
 * @author : Akshay Sharma
 * @since : 11/01/21, Mon
 * https://ak1.io
 **/

/**
 * extension [isDarkThemeOn] checks the saved theme from preference
 * and returns boolean
 */
fun Context.isDarkThemeOn(): Boolean {
    var key = PreferenceManager.getDefaultSharedPreferences(this).getString("list_theme", "3")
    return when (key) {
        "2" -> true
        "1" -> false
        else -> return resources.configuration.uiMode and
                Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
    }

}

/**
 * extension [setupTheme] calls the [AppCompatDelegate] methods which
 * setup the theme whenever the configuration is changed
 */
fun SharedPreferences?.setupTheme(key: String?, resources: Resources) {
    var value = this?.getString(key, "1")
    val def = if (resources.configuration.uiMode and
        Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
    ) {
        AppCompatDelegate.MODE_NIGHT_YES

    } else {
        AppCompatDelegate.MODE_NIGHT_NO
    }
    when (value) {
        "2" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        "1" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        else -> AppCompatDelegate.setDefaultNightMode(def)
    }
}


/**
 * extension [setUpStatusNavigationBarColors] to setup color codes
 * and themes according to themes
 */
fun Window.setUpStatusNavigationBarColors(
    isLight: Boolean = false,
    colorCode: Int = Color.WHITE,
    colorCode2: Int = colorCode
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        statusBarColor = colorCode
        navigationBarColor = colorCode2
    }
    /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        setDecorFitsSystemWindows(isLight)
    } else {*/
    @Suppress("DEPRECATION")
    decorView.systemUiVisibility = if (isLight) {
        0
    } else {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            0
        }
    }
    //}
}

fun Context.getColors(): IntArray {
    return intArrayOf(
        ContextCompat.getColor(this, R.color.home),
        ContextCompat.getColor(this, R.color.logger),
        ContextCompat.getColor(this, R.color.documents),
        ContextCompat.getColor(this, R.color.settings),
        ContextCompat.getColor(this, R.color.home)
    )
}