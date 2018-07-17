package com.androidhuman.example.simplegithub.data.local.sharedpreference

import android.content.Context
import android.preference.PreferenceManager
import io.reactivex.Single

class AuthTokenPreference(private val context: Context) {

    companion object {

        private const val KEY_AUTH_TOKEN = "auth_token"
    }

    fun updateToken(token: String) {
        PreferenceManager.getDefaultSharedPreferences(context).edit()
                .putString(KEY_AUTH_TOKEN, token)
                .apply()
    }

    val token: String?
        get() = PreferenceManager.getDefaultSharedPreferences(context)
                .getString(KEY_AUTH_TOKEN, null)
}
