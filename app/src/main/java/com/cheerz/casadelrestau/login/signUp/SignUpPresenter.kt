package com.cheerz.casadelrestau.login.signUp

import android.content.Context
import com.cheerz.casadelrestau.login.LoginDataChecker
import com.cheerz.casadelrestau.network.data.MiamzSignUp
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class SignUpPresenter(private val view: SignUp.View, private val listener : SignUp.Listener  ) : SignUp.Presenter {

    private val model = SignUpModel()
    private val disposables = CompositeDisposable()

    init {
    }

    override fun onSignInClicked() {
        listener.onSignInClicked()
    }

    override fun onSignUpClicked(email: String, password: String) {
        val loginDataChecker = LoginDataChecker()
        if (loginDataChecker.areAllFieldFilled(email, password) && loginDataChecker.isEmailValid(email))
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
        TODO()
    }
}
