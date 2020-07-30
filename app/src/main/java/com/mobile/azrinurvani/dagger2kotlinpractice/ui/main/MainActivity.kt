package com.mobile.azrinurvani.dagger2kotlinpractice.ui.main

import android.os.Bundle
import android.widget.Toast
import com.mobile.azrinurvani.dagger2kotlinpractice.BaseActivity
import com.mobile.azrinurvani.dagger2kotlinpractice.R

class MainActivity : BaseActivity() {

    companion object{
        private val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(this,"MainActivity",Toast.LENGTH_LONG).show()

    }


}
