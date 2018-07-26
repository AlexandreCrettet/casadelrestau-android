package com.cheerz.casadelrestau.places

import com.cheerz.casadelrestau.network.HttpClient
import com.cheerz.casadelrestau.network.data.MiamzReqPlaceData
import io.reactivex.Single

class PlacesModel : Places.Model {
    override fun fetchPlaces(lat: Double, lng: Double, metersDistance: Int): Single<List<MiamzReqPlaceData>> {
        return HttpClient.service.getPlaces(lat, lng, metersDistance)
    }
}
