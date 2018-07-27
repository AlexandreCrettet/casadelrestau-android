package com.cheerz.casadelrestau.places

import com.cheerz.casadelrestau.network.data.MiamzEvent
import com.cheerz.casadelrestau.network.data.MiamzReqPlaceData
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

object PlacesRepository {

    private var places: List<MiamzReqPlaceData>? = null
    private val observable = PublishSubject.create<List<MiamzReqPlaceData>>()

    fun getObservable(): Observable<List<MiamzReqPlaceData>> {
        return observable
    }

    fun setPlaces(places: List<MiamzReqPlaceData>) {
        this.places = places
        pushEvent()
    }

    fun getPlaceWithId(markerId: Int): MiamzReqPlaceData? {
        return places?.find { it.id == markerId }
    }

    fun updatePlace(placeId: Int, event: MiamzEvent) {
        places?.find { it.id == placeId }?.apply {
            events.add(event)
        }
        pushEvent()
    }

    private fun pushEvent() {
        observable.onNext(places!!)
    }
}
