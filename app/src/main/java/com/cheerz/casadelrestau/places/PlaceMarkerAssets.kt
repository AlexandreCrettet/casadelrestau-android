package com.cheerz.casadelrestau.places

import android.support.annotation.DrawableRes
import com.cheerz.casadelrestau.R

enum class PlaceMarkerAssets(@DrawableRes val asset: Int) {
    FOOD(R.drawable.places_burger_5),
    DRINKS(R.drawable.places_mk_5);

    companion object {
        fun getAssetRes(serverName: String): Int? {
            return when (serverName) {
                "food" -> FOOD.asset
                "drinks" -> DRINKS.asset
                else -> null
            }
        }
    }
}
