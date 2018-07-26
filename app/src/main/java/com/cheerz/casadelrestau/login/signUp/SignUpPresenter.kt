package com.cheerz.casadelrestau.login.signUp

import com.cheerz.casadelrestau.network.data.MiamzSignUp
import com.cheerz.casadelrestau.user.User
import com.cheerz.casadelrestau.user.UserStorage
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class SignUpPresenter(private val view: SignUpView) : Login.Presenter {

    private val model = SignUpModel()
    private val disposables = CompositeDisposable()

    override fun onSignInClicked() {
        view.showSignIn()
    }

    override fun onSignUpClicked(email: String, password: String) {
        if (areAllFieldFilled(email, password) && isEmailValid(email))
            disposables.add(signUp(email, password))
        else
            view.signUpNotValid()
    }

    private fun signUp(email: String, password: String): Disposable {
        return model.signUp(email, password).subscribe({
            onSignedUp(it)
        }, {
            view.signUpNotValid()
        })
    }

    private fun onSignedUp(signUp: MiamzSignUp) {
        UserStorage.storeUser(User(signUp.email, signUp.nickname))
        //TODO: hide login flow
    }

    private fun areAllFieldFilled(email: String, password: String): Boolean {
        return email.isNotEmpty() && password.isNotEmpty()
    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
