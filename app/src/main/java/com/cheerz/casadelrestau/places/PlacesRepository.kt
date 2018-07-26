package com.cheerz.casadelrestau.places

import com.cheerz.casadelrestau.network.data.MiamzReqPlaceData
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

object PlacesRepository {

    private val observable = PublishSubject.create<List<MiamzReqPlaceData>>()

    fun getObservable(): Observable<List<MiamzReqPlaceData>> {
        return observable
    }

    fun setPlaces(places: List<MiamzReqPlaceData>) {
        observable.onNext(places)
    }
}
