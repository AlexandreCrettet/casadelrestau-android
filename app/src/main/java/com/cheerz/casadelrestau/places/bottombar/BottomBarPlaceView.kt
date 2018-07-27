package com.cheerz.casadelrestau.places.bottombar

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import com.cheerz.casadelrestau.R
import com.cheerz.casadelrestau.network.data.MiamzReqPlaceData
import kotlinx.android.synthetic.main.bottom_bar_place_view.view.bottom_bar_address
import kotlinx.android.synthetic.main.bottom_bar_place_view.view.bottom_bar_name

class BottomBarPlaceView(context: Context, attrs: AttributeSet) : BottomBarPlaceMvp.View, ConstraintLayout(context, attrs) {

    init {
        View.inflate(context, R.layout.bottom_bar_place_view, this)
        setBackgroundColor(ContextCompat.getColor(context, R.color.bottom_bar_background))
    }

    override fun fillFields(place: MiamzReqPlaceData) {
        tag = place.id
        bottom_bar_name.text = place.name
        bottom_bar_address.text = place.address
    }
}
