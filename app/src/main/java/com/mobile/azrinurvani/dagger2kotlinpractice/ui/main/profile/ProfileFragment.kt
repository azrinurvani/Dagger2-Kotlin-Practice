package com.mobile.azrinurvani.dagger2kotlinpractice.ui.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.mobile.azrinurvani.dagger2kotlinpractice.R
import dagger.android.support.DaggerFragment

//TODO 50 - Create ProfileFragment class
class ProfileFragment : DaggerFragment(){


    companion object{
        private const val TAG = "ProfileFragment"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Toast.makeText(activity, "Profile Fragment",Toast.LENGTH_LONG).show()

        return inflater.inflate(R.layout.fragment_profile,container,false)

    }
}