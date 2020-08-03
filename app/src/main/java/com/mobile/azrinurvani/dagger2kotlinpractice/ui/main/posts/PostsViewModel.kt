package com.mobile.azrinurvani.dagger2kotlinpractice.ui.main.posts

import android.util.Log
import androidx.lifecycle.ViewModel
import com.mobile.azrinurvani.dagger2kotlinpractice.SessionManager
import com.mobile.azrinurvani.dagger2kotlinpractice.network.main.MainApi
import javax.inject.Inject

//TODO 67 - Create PostsViewModel with 2 parameter (sessionManager,mainApi)
class PostsViewModel @Inject constructor(
    private val sessionManager: SessionManager,
    private val mainApi: MainApi
) : ViewModel() {

    companion object{
        private const val TAG = "PostsViewModel"
    }

    init {
        Log.d(TAG,"PostsViewModel : viewModel is working...")
    }

}