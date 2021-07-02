package com.eslirodrigues.simpletasktodo.util

import android.view.View

fun View.setVisible(show: Boolean) {
    if (show) this.visibility = View.VISIBLE else this.visibility = View.GONE
}