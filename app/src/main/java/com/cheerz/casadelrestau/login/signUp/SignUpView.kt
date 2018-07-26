package com.cheerz.casadelrestau.login

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.widget.RelativeLayout
import android.widget.Toast
import com.cheerz.casadelrestau.R
import kotlinx.android.synthetic.main.login_view.view.*

class LoginView(context: Context, attrs: AttributeSet? = null) : Login.View, ConstraintLayout(context, attrs) {

    private val presenter = LoginPresenter(this)

    init {
        RelativeLayout.inflate(context, R.layout.login_view, this)
        sing_in.setOnClickListener { onSingIngClicked() }
        sing_up.setOnClickListener { onSingUpClicked() }
    }

    override fun onSingIngClicked() {
        presenter.onSignInCicked()
    }

    override fun onSingUpClicked() {
        presenter.onSignUpCicked(email.text.toString(), password.text.toString())
    }

    override fun signUpNotValid() {
        val text = "wrong Data"
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(this.context, text, duration)
        toast.show()
    }
}