package com.cheerz.casadelrestau.login

object LoginDataChecker {

    fun checkData(email: String, password: String): Boolean {
        return areAllFieldFilled(email, password) && isEmailValid(email) && isPasswordEnaugh(password)
    }

    private fun areAllFieldFilled(email: String, password: String): Boolean {
        return email.isNotEmpty() && password.isNotEmpty()
    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isPasswordEnaugh(password: String): Boolean {
        if (password.length < 8)
            return false
        return true
    }
}