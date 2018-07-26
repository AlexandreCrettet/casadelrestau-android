package com.cheerz.casadelrestau.login.singIn

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.cheerz.casadelrestau.R
import com.cheerz.casadelrestau.login.toast
import kotlinx.android.synthetic.main.sign_in_view.view.*

class SignInView(context: Context, attrs: AttributeSet? = null) : SignIn.View, ConstraintLayout(context, attrs) {

    override fun signInNotValid() {
        val text = R.string.sign_up_error_toast_message
        toast(this.context, context.getString(text))
    }

    private val presenter = SignInPresenter(this, context as SignIn.Listener)

    init {
        RelativeLayout.inflate(context, R.layout.sign_in_view, this)
        sign_up.setOnClickListener { onSignUpClicked() }
        sign_in.setOnClickListener { onSignInClicked() }
    }

    private fun onSignInClicked() {
        presenter.onSignInClicked(email.text.toString(), password.text.toString())
    }

    private fun onSignUpClicked() {
        presenter.onSignUpClicked()
    }
}