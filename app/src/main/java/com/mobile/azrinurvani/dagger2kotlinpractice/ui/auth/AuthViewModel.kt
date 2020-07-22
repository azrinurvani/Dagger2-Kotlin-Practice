package com.mobile.azrinurvani.dagger2kotlinpractice.ui.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import com.mobile.azrinurvani.dagger2kotlinpractice.network.AuthApi
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthViewModel @Inject constructor(authApi: AuthApi) : ViewModel() {
    companion object{
        private const val TAG = "AuthViewModel"
    }

    init {
        Log.d(TAG,"AuthViewModel is working...")

        //TODO 17 - Call getUser method from AuthApi
        authApi.getUser(1)
            .toObservable()
            .subscribeOn(Schedulers.io())
            .subscribe ({user->
                Log.d(TAG,"onNext : "+user.email)
            },{
                Log.e(TAG,"onError : "+it.localizedMessage)
            })

    }
}