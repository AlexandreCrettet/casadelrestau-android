package com.cheerz.casadelrestau.login.signUp

interface Login {

    interface View {
        fun onSingIngClicked()
        fun onSingUpClicked()
        fun signUpNotValid()
    }

    interface Presenter {
        fun onSignInCicked()
        fun onSignUpCicked(email: String, password: String)
        fun isAllFieldFill(email: String, password: String): Boolean
    }

    interface Model {
        fun signUp(email: String, password: String)
    }
}
