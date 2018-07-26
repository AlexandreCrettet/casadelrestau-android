package com.cheerz.casadelrestau.login

class LoginDataChecker() {

     fun areAllFieldFilled(email: String, password: String): Boolean {
        return email.isNotEmpty() && password.isNotEmpty()
    }

     fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}