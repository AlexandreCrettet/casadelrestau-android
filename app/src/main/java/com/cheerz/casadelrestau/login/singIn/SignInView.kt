package com.cheerz.casadelrestau.login.singIn

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.cheerz.casadelrestau.R

class SignInView(context: Context, attrs: AttributeSet? = null) : SignIn.View, ConstraintLayout(context, attrs) {

    init {
        RelativeLayout.inflate(context, R.layout.sign_in_view, this)
    }


}