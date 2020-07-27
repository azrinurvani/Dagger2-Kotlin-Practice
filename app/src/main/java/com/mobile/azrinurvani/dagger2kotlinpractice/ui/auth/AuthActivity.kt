package com.mobile.azrinurvani.dagger2kotlinpractice.ui.auth

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.RequestManager
import com.mobile.azrinurvani.dagger2kotlinpractice.R
import com.mobile.azrinurvani.dagger2kotlinpractice.models.User
import com.mobile.azrinurvani.dagger2kotlinpractice.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_auth.*
import javax.inject.Inject


class AuthActivity : DaggerAppCompatActivity() {

    companion object{
        private const val TAG = "AuthActivity"
    }

    @Inject
    lateinit var logo : Drawable

    @Inject
    lateinit var requestManager: RequestManager

//    TODO 8 - Inject ViewModelProviderFactory
    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    lateinit var viewModel : AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        //TODO 9 - initialize viewModel atribute using providerFactory
        viewModel = ViewModelProviders.of(this,providerFactory).get(AuthViewModel::class.java)

        //TODO 24 - Add this for login_button action and call subscribeObserver
        login_button.setOnClickListener {
            attemptLogin()
        }

        subscribeObserver()
        setLogo()
    }

    private fun setLogo(){
        requestManager.load(logo).into(login_logo)
    }

    //TODO 22 - create function to attempt login
    private fun attemptLogin(){
        if (TextUtils.isEmpty(user_id_input.text.toString())){
            return
        }
        viewModel.authenticateWithId(user_id_input.text.toString().toInt())
    }

    //TODO 23 - Create subscribeOrbsever, untuk memproses source yang ada pada authenticateWithId function
    private fun subscribeObserver(){
        //TODO 28 - Delete previous observer method and create new observer viewModel with 4 status AuthResource
       viewModel.observeUser().observe(this, Observer {
            when (it.status){
                AuthResource.AuthStatus.LOADING -> {
                    showProgressBar(true)
                }

                AuthResource.AuthStatus.ERROR -> {
                    showProgressBar(false)
                    Toast.makeText(this,it.message+ "\nDid you enter a number between 1 and 10? ",Toast.LENGTH_LONG).show()
                }

                AuthResource.AuthStatus.AUTHENTICATED -> {
                    showProgressBar(false)
                    Log.d(TAG, "onChanged : LOGIN SUCCESS : "+it.data?.email)
                }

                AuthResource.AuthStatus.NOT_AUTHENTICATED -> {
                    showProgressBar(false)
                }

            }
       })
    }

    //TODO 29 - Create new method for handle visibility progress bar
    private fun showProgressBar(isVisible : Boolean){
        if (isVisible){
            progress_bar.visibility = View.VISIBLE
        }else{
            progress_bar.visibility = View.GONE
        }
    }

}
