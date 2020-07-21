package com.mobile.azrinurvani.dagger2kotlinpractice.ui.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import com.mobile.azrinurvani.dagger2kotlinpractice.network.AuthApi
import javax.inject.Inject

class AuthViewModel @Inject constructor(authApi: AuthApi) : ViewModel() {
    companion object{
        private const val TAG = "AuthViewModel"
    }

    init {
        Log.d(TAG,"AuthViewModel is working...")
        if (authApi ==null){
            Log.d(TAG, "AuthViewModel: api is NULL")
        } else {
            Log.d(TAG, "AuthViewModel: api is not NULL")
        }

    }
}