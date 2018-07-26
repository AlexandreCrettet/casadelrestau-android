package com.cheerz.casadelrestau.login.signUp

interface Login {

    interface View {
        fun onSingIngClicked()
        fun onSingUpClicked()
        fun signUpNotValid()
    }

    interface Presenter {
        fun onSignInClicked()
        fun onSignUpClicked(email: String, password: String)
        fun isAllFieldFill(email: String, password: String): Boolean
    }

    interface Model {
        fun signUp(email: String, password: String)
        fun login(email: String, password: String)
    }
}
