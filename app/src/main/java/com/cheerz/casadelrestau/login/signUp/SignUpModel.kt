package com.cheerz.casadelrestau.login.signUp

import com.cheerz.casadelrestau.network.HttpClient
import com.cheerz.casadelrestau.network.data.MiamzLogin
import com.cheerz.casadelrestau.network.data.MiamzReqLogin
import com.cheerz.casadelrestau.network.data.MiamzReqSignUp
import com.cheerz.casadelrestau.network.data.MiamzSignUp
import io.reactivex.Single

class SignUpModel : Login.Model {

    override fun login(email: String, password: String): Single<MiamzLogin> {
        return HttpClient.service.login(MiamzReqLogin(email, password))
    }

    override fun signUp(email: String, password: String): Single<MiamzSignUp> {
        return HttpClient.service.signUp(MiamzReqSignUp(email, password))
    }
}
