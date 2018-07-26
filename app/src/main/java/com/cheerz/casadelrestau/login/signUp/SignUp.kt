package com.cheerz.casadelrestau.login.signUp

import com.cheerz.casadelrestau.network.data.MiamzLogin
import com.cheerz.casadelrestau.network.data.MiamzSignUp
import io.reactivex.Single

interface Login {

    interface View {
        fun onSingIngClicked()
        fun onSingUpClicked()
        fun signUpNotValid()
        fun showSignIn()
    }

    interface Presenter {
        fun onSignInClicked(email: String, password: String)
        fun onSignUpClicked(email: String, password: String)
    }

    interface Model {
        fun signUp(email: String, password: String): Single<MiamzSignUp>
        fun login(email: String, password: String): Single<MiamzLogin>
    }
}
