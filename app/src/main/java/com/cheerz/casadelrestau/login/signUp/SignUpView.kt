package com.cheerz.casadelrestau.login.signUp

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.widget.RelativeLayout
import android.widget.Toast
import com.cheerz.casadelrestau.R
import kotlinx.android.synthetic.main.login_view.view.*

class SignUpView(context: Context, attrs: AttributeSet? = null) : Login.View, ConstraintLayout(context, attrs) {

    private val presenter = SignUpPresenter(this)

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
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(this.context, text, duration)
        toast.show()
    }

    override fun showSignIn() {
        TODO()
    }
}