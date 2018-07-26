package com.cheerz.casadelrestau.login.signUp

import com.cheerz.casadelrestau.network.data.MiamzLogin
import com.cheerz.casadelrestau.network.data.MiamzSignUp
import io.reactivex.Single

interface SignUp {

    interface View {
        fun signUpNotValid()
        fun passwordTooShort()
    }

    interface Presenter {
        fun onSignUpClicked(email: String, password: String)
        fun onSignInClicked()
    }

    interface Model {
        fun signUp(email: String, password: String): Single<MiamzSignUp>
        fun login(email: String, password: String): Single<MiamzLogin>
    }

    interface Listener {
        fun onSignInClicked()
    }
}
