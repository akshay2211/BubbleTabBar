package com.fxn.bubbletabbarapp

import android.app.Application
import androidx.preference.PreferenceManager
import com.fxn.bubbletabbarapp.utils.setupTheme

/**
 * @author : Akshay Sharma
 * @since : 11/01/21, Mon
 * akshay2211.github.io
 **/
class App : Application() {
    override fun onCreate() {
        super.onCreate()

        PreferenceManager.getDefaultSharedPreferences(this).setupTheme("list_theme", resources)
    }
}