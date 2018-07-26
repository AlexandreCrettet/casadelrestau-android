package com.cheerz.casadelrestau.network

import com.cheerz.casadelrestau.network.data.MiamzLogin
import com.cheerz.casadelrestau.network.data.MiamzReqLogin
import com.cheerz.casadelrestau.network.data.MiamzReqSignUp
import com.cheerz.casadelrestau.network.data.MiamzSignUp
import io.reactivex.Single
import retrofit2.http.POST

interface CasaDelRestauService {

    companion object {
        const val BASE_URL = "https://www.TODO"
    }

    @POST("login")
    fun login(params: MiamzReqLogin): Single<MiamzLogin>

    @POST("api/1/users")
    fun signUp(params: MiamzReqSignUp): Single<MiamzSignUp>
}
