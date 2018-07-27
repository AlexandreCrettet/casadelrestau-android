package com.cheerz.casadelrestau

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.widget.Toast
import kotlinx.android.synthetic.main.book_place.view.*

class CreateEventView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    init {
        inflate(context, R.layout.book_place, this)
        bookButton.setOnClickListener { bookButtonClicked() }
    }

    fun bookButtonClicked() {
        if (hour.text.toString().toInt() in 24 downTo -1 && minutes.text.toString().toInt() in 59 downTo -1)
            TODO( "OK l'heure est good" )
        else
            toast(this.context, "Use proper value for your reservation")
    }
}
