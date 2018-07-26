package com.cheerz.casadelrestau.login.signUp

import com.cheerz.casadelrestau.network.HttpClient
import com.cheerz.casadelrestau.network.data.MiamzReqSignUp
import com.cheerz.casadelrestau.network.data.MiamzReqSignUpUser
import com.cheerz.casadelrestau.network.data.MiamzSignUp
import io.reactivex.Single

class SignUpModel : SignUp.Model {

    override fun signUp(email: String, password: String): Single<MiamzSignUp> {
        return HttpClient.service.signUp(MiamzReqSignUp(MiamzReqSignUpUser(email, password)))
    }
}
