package com.cheerz.casadelrestau.login.signUp

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.widget.RelativeLayout
import android.widget.Toast
import com.cheerz.casadelrestau.R
import com.cheerz.casadelrestau.login.toast
import kotlinx.android.synthetic.main.login_view.view.*

class SignUpView(context: Context, attrs: AttributeSet? = null) : SignUp.View, ConstraintLayout(context, attrs) {

    private val presenter = SignUpPresenter(this, context as SignUp.Listener)

    init {
        RelativeLayout.inflate(context, R.layout.login_view, this)
        sign_in.setOnClickListener { onSingIngClicked() }
        sign_up.setOnClickListener { onSingUpClicked() }
    }

    override fun onSingIngClicked() {
        presenter.onSignInClicked()
    }

    override fun onSingUpClicked() {
        presenter.onSignUpClicked(email.text.toString(), password.text.toString())
    }

    override fun signUpNotValid() {
        val text = R.string.sign_up_error_toast_message
        toast(this.context,   context.getString(text))
    }
}