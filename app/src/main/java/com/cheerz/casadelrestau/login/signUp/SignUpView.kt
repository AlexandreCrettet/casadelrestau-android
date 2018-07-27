package com.cheerz.casadelrestau.login.signUp

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.cheerz.casadelrestau.R
import com.cheerz.casadelrestau.toast
import kotlinx.android.synthetic.main.login_view.view.*

class SignUpView(context: Context, attrs: AttributeSet? = null) : SignUp.View, ConstraintLayout(context, attrs) {

    override fun passwordTooShort() {
        val text = R.string.password_too_short_toast_message
        toast(this.context, context.getString(text))
    }

    private val presenter = SignUpPresenter(this, context as SignUp.Listener)

    init {
        RelativeLayout.inflate(context, R.layout.login_view, this)
        setBackgroundColor(ContextCompat.getColor(context, R.color.pink))
        log_in.setOnClickListener { onSignInClicked() }
        sign_up.setOnClickListener { onSingUpClicked() }
    }

    private fun onSignInClicked() {
        presenter.onSignInClicked()
    }

    private fun onSingUpClicked() {
        presenter.onSignUpClicked(email.text.toString(), password.text.toString())
    }

    override fun signUpNotValid() {
        val text = R.string.sign_up_error_toast_message
        toast(this.context, context.getString(text))
    }
}