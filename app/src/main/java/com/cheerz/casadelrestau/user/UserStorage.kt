package com.cheerz.casadelrestau.user

import android.content.Context
import android.content.SharedPreferences

object UserStorage {
    private val prefsName = "com.cheerz.casadelrestau.user.prefsName"
    private val prefsEmailKey = "com.cheerz.casadelrestau.user.email"
    private val prefsTokenKey = "com.cheerz.casadelrestau.user.token"
    private val prefsUserNameKey = "com.cheerz.casadelrestau.user.userName"
    private var prefs: SharedPreferences? = null

    fun initialize(context: Context) {
        prefs = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE)
    }

    fun storeUser(user: User) {
        prefs!!.edit().apply {
            putString(prefsEmailKey, user.email)
            putString(prefsUserNameKey, user.username)
        }.apply()
    }

    fun retrieveUser(): User? {
        return prefs!!.run {
            val email = getString(prefsEmailKey, null) ?: return null
            val username = getString(prefsUserNameKey, null) ?: return null
            User(email, username)
        }
    }

    fun storeToken(token: String) {
        prefs!!.edit().putString(prefsTokenKey, token).apply()
    }

    fun retrieveToken(): String? {
        return prefs!!.getString(prefsTokenKey, null)
    }
}
