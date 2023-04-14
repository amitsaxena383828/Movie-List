package com.assignment.interviewdemo.utils

import android.app.Activity
import android.content.Context
import android.os.IBinder
import android.view.inputmethod.InputMethodManager

object Utility {
    fun hideKeyboard(activity: Activity?) {
        if (activity == null) return
        val view = activity.currentFocus
        if (view != null) {
            hideKeyboard(activity, view.windowToken)
        }
    }

    private fun hideKeyboard(activity: Activity?, windowToken: IBinder?) {
        if (activity == null) return
        val inputManager =
            activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }
}