package com.cheerz.casadelrestau

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.widget.RelativeLayout

class bookPlaces(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    init {
        RelativeLayout.inflate(context, R.layout.book_place, this)
    }
}