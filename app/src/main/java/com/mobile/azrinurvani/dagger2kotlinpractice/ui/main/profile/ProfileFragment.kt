package com.mobile.azrinurvani.dagger2kotlinpractice.ui.main.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mobile.azrinurvani.dagger2kotlinpractice.R
import com.mobile.azrinurvani.dagger2kotlinpractice.models.User
import com.mobile.azrinurvani.dagger2kotlinpractice.ui.auth.AuthResource
import com.mobile.azrinurvani.dagger2kotlinpractice.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject

//TODO 50 - Create ProfileFragment class
class ProfileFragment : DaggerFragment(){

    lateinit var viewModel : ProfileViewModel

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    companion object{
        private const val TAG = "ProfileFragment"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Toast.makeText(activity, "Profile Fragment",Toast.LENGTH_LONG).show()

        return inflater.inflate(R.layout.fragment_profile,container,false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG,"onViewCreated: ProfileFragment was created...")

        //TODO 56 - Init ProfileViewModel on UI(ProfileFragment)
        viewModel = ViewModelProviders.of(this,providerFactory).get(ProfileViewModel::class.java)

        //TODO 61 - call subscribeObservers method
        subscribeObservers()
    }

    //TODO 59 - Create subscribeObserve method to observe auth user data
    private fun subscribeObservers(){
        viewModel.getAuthenticatedUser().removeObservers(viewLifecycleOwner)
        viewModel.getAuthenticatedUser().observe(viewLifecycleOwner,
            Observer<AuthResource<User>> { userAuthResource ->
                if(userAuthResource!=null){
                    when(userAuthResource.status){
                        AuthResource.AuthStatus.AUTHENTICATED ->{
                            setUserDetails(userAuthResource.data)
                        }
                        AuthResource.AuthStatus.ERROR -> {
                            setErrorDetails(userAuthResource.message)
                        }
                    }
                }
            })
    }

    //TODO 60 - Create setUserDetails and setErrorDetails method to handling if user authenticated or error
    private fun setUserDetails(data: User?) {
        email.text = data?.email
        username.text = data?.username
        website.text = data?.website
    }
    private fun setErrorDetails(message: String?) {
        email.text = message
        username.text = "error"
        website.text = "error"
    }




}