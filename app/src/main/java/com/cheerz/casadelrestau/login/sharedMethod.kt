package com.cheerz.casadelrestau.login

import android.content.Context
import android.view.View
import android.widget.Toast

fun View.show() {
    visibility= View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun toast(context : Context, text : String) {
    val duration = Toast.LENGTH_SHORT
    val toast = Toast.makeText(context, text, duration)
    toast.show()
}