package com.cheerz.casadelrestau.places.bottombar

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.View
import com.cheerz.casadelrestau.R

class BottomBarPlaceView(context: Context, attrs: AttributeSet) : BottomBarPlaceMvp.View, ConstraintLayout(context, attrs) {

    init {
        View.inflate(context, R.layout.bottom_bar_place_view, this)
    }
}
