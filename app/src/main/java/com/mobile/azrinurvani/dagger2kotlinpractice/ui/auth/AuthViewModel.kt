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
import io.reactivex.functions.Function
import javax.inject.Inject


class AuthViewModel @Inject constructor(private val authApi: AuthApi) : ViewModel() {

//    TODO 19 - Create private mediator live data variabel
//    private val authUser = MediatorLiveData<User>()
//    TODO 26 - Change authUser object
    private val authUser = MediatorLiveData<AuthResource<User>>()

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
        //TODO 27 - add this line for set value auth resource as loading
        authUser.value = AuthResource.loading()

        val source : LiveData<AuthResource<User>> = LiveDataReactiveStreams.fromPublisher(
            authApi.getUser(userId).
                //TODO 29 - Create onErrorReturn and Map
                //instead calling on error happen
                onErrorReturn {
                    var errorUser = User()
                    errorUser.id = -1
                    errorUser
                }.
                map(object : Function<User,AuthResource<User>>{
                    override fun apply(user: User): AuthResource<User> {
                        if (user.id == -1){
                            return AuthResource.error("Could not authenticate")
                        }
                        return AuthResource.authenticated(user)
                    }
                }).
                subscribeOn(Schedulers.io())
        )

        //TODO 30 - Change ouput Observer to AuthResource<User>
        authUser.addSource(source, object : Observer<User?>, androidx.lifecycle.Observer<AuthResource<User>> {
            override fun onChanged(user: AuthResource<User>) {
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
    fun observeUser() : LiveData<AuthResource<User>>{
        return authUser
    }
}