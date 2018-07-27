package com.cheerz.casadelrestau.places.bottombar

import com.cheerz.casadelrestau.network.data.MiamzReqPlaceData

interface BottomBarPlaceMvp {
    interface View {
        fun fillFields(place: MiamzReqPlaceData)
    }

    interface Model
    interface Presenter
}
