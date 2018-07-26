package com.cheerz.casadelrestau.network

import com.cheerz.casadelrestau.network.data.MiamzLogin
import com.cheerz.casadelrestau.network.data.MiamzSignUp
import io.reactivex.Single
import retrofit2.http.POST

interface CasaDelRestauService {

    companion object {
        const val BASE_URL = "https://www.TODO"
    }

    @POST("login")
    fun login(email: String, password: String): Single<MiamzLogin>

    @POST("register")
    fun signUp(email: String, password: String): Single<MiamzSignUp>
}
