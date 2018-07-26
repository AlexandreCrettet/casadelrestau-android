package com.cheerz.casadelrestau.login.singIn

import com.cheerz.casadelrestau.login.LoginDataChecker
import com.cheerz.casadelrestau.network.data.MiamzLogin
import com.cheerz.casadelrestau.user.User
import com.cheerz.casadelrestau.user.UserStorage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SignInPresenter(private val view: SignInView,
                      private val listener: SignIn.Listener) : SignIn.Presenter {

    private val model: SignIn.Model = SignInModel()
    private val disposables = CompositeDisposable()

    override fun onSignInClicked(email : String, password : String) {
        if (LoginDataChecker.areAllFieldFilled(email, password) && LoginDataChecker.isEmailValid(email))
            disposables.add(login(email, password))
        else
            view.signInNotValid()
    }

    private fun login(email: String, password: String): Disposable {
        return model.login(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    onLoggedIn(it)
                }, {
                    view.signInNotValid()
                })

    }

    private fun onLoggedIn(login: MiamzLogin) {
        UserStorage.storeUser(User(login.email, login.nickname))
        //TODO: hide login flow and show map
    }

    override fun onSignUpClicked() {
        listener.onSignUpClicked()
    }
}
