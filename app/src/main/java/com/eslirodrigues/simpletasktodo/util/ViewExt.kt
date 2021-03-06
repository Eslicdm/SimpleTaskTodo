package com.eslirodrigues.simpletasktodo.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

fun View.setVisible(show: Boolean) {
    if (show) this.visibility = View.VISIBLE else this.visibility = View.GONE
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

//fun Fragment.hideKeyboard() {
//    view?.let { activity?.hideKeyboard(it) }
//}
//
//fun Activity.hideKeyboard() {
//    hideKeyboard(currentFocus ?: View(this))
//}
//
//fun Context.hideKeyboard(view: View) {
//    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
//    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
//}