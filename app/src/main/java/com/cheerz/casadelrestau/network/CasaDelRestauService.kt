package com.cheerz.casadelrestau.network

import com.cheerz.casadelrestau.network.data.MiamzLogin
import com.cheerz.casadelrestau.network.data.MiamzPlace
import com.cheerz.casadelrestau.network.data.MiamzReqLogin
import com.cheerz.casadelrestau.network.data.MiamzReqPlace
import com.cheerz.casadelrestau.network.data.MiamzReqSignUp
import com.cheerz.casadelrestau.network.data.MiamzSignUp
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CasaDelRestauService {

    companion object {
        const val BASE_URL = "https://floating-earth-31565.herokuapp.com/api/1/"
    }

    @POST("session")
    fun login(@Body params: MiamzReqLogin): Single<MiamzLogin>

    @POST("users")
    fun signUp(@Body params: MiamzReqSignUp): Single<MiamzSignUp>

    @POST("places")
    fun postNewPlace(): Single<MiamzReqPlace>

    @GET("places")
    fun getPlaces(@Body place: MiamzPlace): Single<MiamzReqPlace>
}
