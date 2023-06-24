package com.example.mytask.utils

import android.content.Context
/*
* Object class fro sharedpreference calls
* */

object SharedPrefUtil {
    const val SHARED_PREF_NAME = "mytask"
    const val FEV_PRODUCT_DATA = "fev_data"
    const val SELECTED_PRODUCT_DATA = "product_data"

    fun setFevProduct(mContext: Context, userdata: String) {
        val mPrefs = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val prefsEditor = mPrefs.edit()
        prefsEditor.putString(FEV_PRODUCT_DATA, userdata)
        prefsEditor.apply()
    }

    fun getFevProduct(mContext: Context): String? {
        val mPrefs = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        return mPrefs.getString(FEV_PRODUCT_DATA, "")
    }

    fun setUserData(mContext: Context, userdata: String) {
        val mPrefs = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val prefsEditor = mPrefs.edit()
        prefsEditor.putString(SELECTED_PRODUCT_DATA, userdata)
        prefsEditor.apply()
    }

    fun getUserData(mContext: Context): String? {
        val mPrefs = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        return mPrefs.getString(SELECTED_PRODUCT_DATA, "")
    }

}