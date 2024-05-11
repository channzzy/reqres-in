package com.example.reqresin

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context : Context) {
    var sharedPreferences : SharedPreferences? = null
    var editor :SharedPreferences.Editor? = null

    val keyPreferences = "token"
    val auth = "Bearer"

    init {
        sharedPreferences = context.getSharedPreferences(keyPreferences, Context.MODE_PRIVATE)
        editor = sharedPreferences?.edit()
    }

    fun sessionLogin(token : String){
        editor?.putString(auth,token)
        editor?.apply()
    }
    fun getToken(): String? {
        return sharedPreferences?.getString(auth, null)
    }
}