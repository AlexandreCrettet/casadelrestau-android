package com.cheerz.casadelrestau.places

import com.cheerz.casadelrestau.network.data.MiamzReqPlaceData
import io.reactivex.Single

interface Places {
    interface View {
        fun showPlaces(places: List<MiamzReqPlaceData>)
    }

    interface Presenter {
        fun onMapShown(lat: Double, lng: Double, distanceSeenMeters: Int)
    }

    interface Model {
        fun fetchPlaces(lat: Double, lng: Double, metersDistance: Int): Single<List<MiamzReqPlaceData>>
    }
}
