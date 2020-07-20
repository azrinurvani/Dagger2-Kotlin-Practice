package com.mobile.azrinurvani.dagger2kotlinpractice.ui.auth

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.RequestManager
import com.mobile.azrinurvani.dagger2kotlinpractice.R
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

        //TODO 9 - initialize viewModel atribut using providerFactory
        viewModel = ViewModelProviders.of(this,providerFactory).get(AuthViewModel::class.java)

        setLogo()
    }

    private fun setLogo(){
        requestManager.load(logo).into(login_logo)
    }
}
