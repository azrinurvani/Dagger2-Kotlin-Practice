package com.mobile.azrinurvani.dagger2kotlinpractice.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.mobile.azrinurvani.dagger2kotlinpractice.models.User
import com.mobile.azrinurvani.dagger2kotlinpractice.network.AuthApi
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class AuthViewModel @Inject constructor(private val authApi: AuthApi) : ViewModel() {

//    TODO 19 - Create private mediator live data variabel
    private val authUser = MediatorLiveData<User>()

    companion object{
        private const val TAG = "AuthViewModel"
    }

    init {
        Log.d(TAG,"AuthViewModel is working...")

        //TODO 17 - Call getUser method from AuthApi
        //TODO 18 - Delete this block program base TODO 17
//        authApi.getUser(1)
//            .toObservable()
//            .subscribeOn(Schedulers.io())
//            .subscribe ({user->
//                Log.d(TAG,"onNext : "+user.email)
//            },{
//                Log.e(TAG,"onError : "+it.localizedMessage)
//            })

    }
    //TODO 21 - create auhthtenticateWithId function, merubah source berupa live data ke dalam bentuk observer
    fun authenticateWithId(userId :Int) {
        val source : LiveData<User> = LiveDataReactiveStreams.fromPublisher(
            authApi.getUser(userId).
                    subscribeOn(Schedulers.io())
        )
        authUser.addSource(source, object : Observer<User?>, androidx.lifecycle.Observer<User> {
            override fun onChanged(user: User?) {
                authUser.value = user
                authUser.removeSource(source)
            }

            override fun onComplete() {

            }

            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: User) {

            }

            override fun onError(e: Throwable) {

            }
        })
    }


    //TODO 20 - Create observeUser function to return authUser
    fun observeUser() : LiveData<User>{
        return authUser
    }
}