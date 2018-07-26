package com.cheerz.casadelrestau.login.signUp

class SignUpPresenter(private val view: SignUpView) : Login.Presenter {

    private val model = SignUpModel()

    override fun onSignInCicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSignUpCicked(email: String, password: String) {
        if (isAllFieldFill(email, password) && isEmailValid(email))
            model.signUp(email, password)
        else
            view.signUpNotValid()
    }

    override fun isAllFieldFill(email: String, password: String) : Boolean {
        return email.isNotEmpty() && password.isNotEmpty()
    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

}