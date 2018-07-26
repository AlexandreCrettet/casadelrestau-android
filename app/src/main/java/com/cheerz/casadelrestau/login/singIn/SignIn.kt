package com.cheerz.casadelrestau.login.singIn

import com.cheerz.casadelrestau.network.data.MiamzLogin
import io.reactivex.Single

interface SignIn {

    interface View {
        fun signInNotValid()
    }

    interface Presenter {
        fun onSignUpClicked()
        fun onSignInClicked(email: String, password : String)

    }

    interface Model {
        fun login(email: String, password: String): Single<MiamzLogin>
    }

    interface Listener {
        fun onSignUpClicked()
        fun goToMapView()
    }
}
