package com.cheerz.casadelrestau.places

import android.support.annotation.DrawableRes
import com.cheerz.casadelrestau.R

enum class PlaceMarkerAssets(@DrawableRes val asset: Int) {
    FOOD(R.drawable.ic_launcher_background),
    DRINKS(R.drawable.ic_launcher_background);

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
