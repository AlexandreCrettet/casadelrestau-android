package com.cheerz.casadelrestau.network

import com.cheerz.casadelrestau.network.data.MiamzEvent
import com.cheerz.casadelrestau.network.data.MiamzLogin
import com.cheerz.casadelrestau.network.data.MiamzReqEventWrapper
import com.cheerz.casadelrestau.network.data.MiamzReqLogin
import com.cheerz.casadelrestau.network.data.MiamzReqPlace
import com.cheerz.casadelrestau.network.data.MiamzReqPlaceData
import com.cheerz.casadelrestau.network.data.MiamzReqSignUp
import com.cheerz.casadelrestau.network.data.MiamzSignUp
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface CasaDelRestauService {

    companion object {
        const val BASE_URL = "https://floating-earth-31565.herokuapp.com/api/1/"
    }

    @POST("session")
    fun login(@Body params: MiamzReqLogin): Single<MiamzLogin>

    @POST("users")
    fun signUp(@Body params: MiamzReqSignUp): Single<MiamzSignUp>

    @POST("places")
    fun postNewPlace(@Body place: MiamzReqPlace): Single<MiamzReqPlaceData>

    @GET("places")
    fun getPlaces(@Query("lat") lat: Double, @Query("lng") lng: Double, @Query("meter_distance") meter_distance: Int): Single<List<MiamzReqPlaceData>>

    @POST("events")
    fun postNewEvent(@Body event: MiamzReqEventWrapper): Single<MiamzEvent>

    @POST("events/{event_id}/event_registration")
    fun postEventRegistration(@Path("event_id") eventId: Int): Single<MiamzEvent>

    @DELETE("events/{event_id}/event_registration")
    fun deleteEventRegistration(@Path("event_id") eventId: Int): Single<MiamzEvent>
}
