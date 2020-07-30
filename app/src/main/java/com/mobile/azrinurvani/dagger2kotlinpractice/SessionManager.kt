package com.mobile.azrinurvani.dagger2kotlinpractice

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.mobile.azrinurvani.dagger2kotlinpractice.models.User
import com.mobile.azrinurvani.dagger2kotlinpractice.ui.auth.AuthResource
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import javax.inject.Inject
import javax.inject.Singleton

//TODO 31 - Create SessionManager class with Singleton scope
@Singleton
class SessionManager @Inject constructor() {

    companion object{
        private val TAG = "SessionManager"
    }

    private val cachedUser = MediatorLiveData<AuthResource<User>>()

    //TODO 32 - Create authenticateWithId (same with authenticationWithId on ViewModel)
    fun authenticateWithId(source : LiveData<AuthResource<User>>) {
        if (cachedUser!=null){
            cachedUser.value = AuthResource.loading()
            cachedUser.addSource(source, object : Observer<User>,androidx.lifecycle.Observer<AuthResource<User>>{
                override fun onChanged(userAuthResource: AuthResource<User>?) {
                    cachedUser.value = userAuthResource
                    cachedUser.removeSource(source)
                }

                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {

                }

                override fun onNext(user: User) {

                }

            })
        }
    }

    //TODO 33 - Create logout function
    fun logout(){
        Log.d(TAG,"logOut : Logging out....")
        cachedUser.value = AuthResource.logout()
    }

    //TODO 34 - Create getAuthUser() function to return live data
    fun getAuthUser(): LiveData<AuthResource<User>>{
        return cachedUser
    }

}