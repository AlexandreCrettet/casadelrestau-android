package com.cheerz.casadelrestau

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet

class CreateEventView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    init {
        inflate(context, R.layout.book_place, this)
    }
}
