package com.mobile.azrinurvani.dagger2kotlinpractice.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.mobile.azrinurvani.dagger2kotlinpractice.SessionManager
import com.mobile.azrinurvani.dagger2kotlinpractice.models.User
import com.mobile.azrinurvani.dagger2kotlinpractice.network.auth.AuthApi
import io.reactivex.schedulers.Schedulers
import io.reactivex.functions.Function
import javax.inject.Inject


class AuthViewModel @Inject constructor(
    private val authApi: AuthApi,
    private val sessionManager: SessionManager
) : ViewModel() {

//    TODO 19 - Create private mediator live data variabel
//    private val authUser = MediatorLiveData<User>()
//    TODO 26 - Change authUser object
//    TODO 37 - Delete authUserObject
//    private val authUser = MediatorLiveData<AuthResource<User>>()

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
    //TODO 21 - create auhthenticateWithId function, merubah source berupa live data ke dalam bentuk observer
    fun authenticateWithId(userId :Int) {
        //TODO 27 - add this line for set value auth resource as loading
        //TODO 39 - Delete AuthResource loading value, because that declare in SessionManager
//        authUser.value = AuthResource.loading()

        //TODO 30 - Change ouput Observer to AuthResource<User>
        //TODo 41 - Delete addSource and change with sessionManager function
        sessionManager.authenticateWithId(queryUserId(userId))
    }

    //TODO 40 - Create new method to return LiveDataReactiveStreams
    private fun queryUserId(id : Int) : LiveData<AuthResource<User>>{
        return LiveDataReactiveStreams.fromPublisher(
            authApi.getUser(id).
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
    }


    //TODO 20 - Create observeUser function to return authUser
    //TODO 38 - rename this function and change return value
    fun observeAuthState() : LiveData<AuthResource<User>>{
        return sessionManager.getAuthUser()
    }
}