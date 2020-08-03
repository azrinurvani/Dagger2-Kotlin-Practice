package com.mobile.azrinurvani.dagger2kotlinpractice.ui.main.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mobile.azrinurvani.dagger2kotlinpractice.SessionManager
import com.mobile.azrinurvani.dagger2kotlinpractice.models.User
import com.mobile.azrinurvani.dagger2kotlinpractice.ui.auth.AuthResource
import javax.inject.Inject

//TODO 53 - Create ProfileViewModel
class ProfileViewModel @Inject constructor(
    //TODO 57 Add parameter for init SessionManager
    private val sessionManager: SessionManager
) : ViewModel() {

    companion object {
        private const val TAG = "ProfileViewModel"
    }

    init {
        Log.d(TAG,"ProfileViewModel : viewModel is ready...")
    }

    //TODO 58 - Create this method to return auth user from sessionManger
    fun getAuthenticatedUser() : LiveData<AuthResource<User>>{
        return sessionManager.getAuthUser()
    }


}