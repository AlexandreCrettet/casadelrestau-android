package com.cheerz.casadelrestau.login.singIn

import com.cheerz.casadelrestau.network.HttpClient
import com.cheerz.casadelrestau.network.data.MiamzLogin
import com.cheerz.casadelrestau.network.data.MiamzReqLogin
import com.cheerz.casadelrestau.network.data.MiamzReqLoginSession
import io.reactivex.Single

class SignInModel : SignIn.Model {
    override fun login(email: String, password: String): Single<MiamzLogin> {
        return HttpClient.service.login(MiamzReqLogin(MiamzReqLoginSession(email, password)))
    }
}
