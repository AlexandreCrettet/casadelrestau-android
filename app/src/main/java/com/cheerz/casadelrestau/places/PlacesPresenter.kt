package com.cheerz.casadelrestau.places

import com.cheerz.casadelrestau.network.data.MiamzReqPlaceData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PlacesPresenter(private val view: Places.View,
                      private val model: Places.Model) : Places.Presenter {

    init {
        PlacesRepository.getObservable().subscribe { view.showPlaces(it) }
    }

    override fun onMapShown(lat: Double, lng: Double, distanceSeenMeters: Int) {
        fetchPlaces(lat, lng, distanceSeenMeters)
    }

    private fun fetchPlaces(lat: Double, lng: Double, distanceSeenMeters: Int) {
        model.fetchPlaces(lat, lng, distanceSeenMeters)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(2)
                .subscribe({ onPlacesFetched(it) }, {})
    }

    private fun onPlacesFetched(places: List<MiamzReqPlaceData>) {
        PlacesRepository.setPlaces(places)
    }
}
