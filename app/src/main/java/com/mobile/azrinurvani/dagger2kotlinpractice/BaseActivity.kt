package com.mobile.azrinurvani.dagger2kotlinpractice

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import com.mobile.azrinurvani.dagger2kotlinpractice.models.User
import com.mobile.azrinurvani.dagger2kotlinpractice.ui.auth.AuthActivity
import com.mobile.azrinurvani.dagger2kotlinpractice.ui.auth.AuthResource
import com.mobile.azrinurvani.dagger2kotlinpractice.ui.main.MainActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


//TODO 42 - Create BaseActivity
abstract class BaseActivity : DaggerAppCompatActivity(){

    companion object {
        private val TAG = "BaseActivity"
    }

    @Inject
    lateinit var sessionManager : SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeObservers()
    }

    //TODO 43 - Create this method for managing session, this same with AuthActivity
    private fun subscribeObservers(){
        sessionManager.getAuthUser().observe(this,
            Observer<AuthResource<User>> { userAuthResource ->
                when (userAuthResource?.status){
                    AuthResource.AuthStatus.LOADING -> {

                    }

                    AuthResource.AuthStatus.ERROR -> {
                        Toast.makeText(applicationContext, userAuthResource.message + "\nDid you enter a number between 1 and 10? ",
                            Toast.LENGTH_LONG).show()
                    }

                    AuthResource.AuthStatus.AUTHENTICATED -> {
                        Log.d(TAG, "onChanged : LOGIN SUCCESS : "+ userAuthResource.data?.email)
                        //                        onLoginSuccess()
                    }

                    AuthResource.AuthStatus.NOT_AUTHENTICATED -> {
                        navLoginScreen()
                    }
                }
            })
    }

    //TODO 44 - Create method for Intent if Login Success and not Success
    private fun onLoginSuccess(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun navLoginScreen(){
        val intent = Intent(this,AuthActivity::class.java)
        startActivity(intent)
        finish()
    }
}