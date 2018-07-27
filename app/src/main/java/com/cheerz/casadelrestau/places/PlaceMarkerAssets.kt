package com.cheerz.casadelrestau.places

import android.support.annotation.DrawableRes
import com.cheerz.casadelrestau.R

enum class PlaceMarkerAssets(@DrawableRes val markerAssets: Int, @DrawableRes val pinkAssets: Int) {
    FOOD(R.drawable.places_burger_5, R.drawable.pinkburger),
    DRINKS(R.drawable.places_mk_5, R.drawable.pinkmk);


    companion object {
        fun find(serverName: String): PlaceMarkerAssets? {
            return when (serverName) {
                "food" -> FOOD
                "drinks" -> DRINKS
                else -> null
            }
        }
    }
}
