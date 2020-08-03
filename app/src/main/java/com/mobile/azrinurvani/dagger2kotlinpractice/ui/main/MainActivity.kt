package com.mobile.azrinurvani.dagger2kotlinpractice.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.mobile.azrinurvani.dagger2kotlinpractice.BaseActivity
import com.mobile.azrinurvani.dagger2kotlinpractice.R
import com.mobile.azrinurvani.dagger2kotlinpractice.ui.main.profile.ProfileFragment

class MainActivity : BaseActivity() {

    companion object{
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(this,"MainActivity",Toast.LENGTH_LONG).show()
        testFragment()
    }

    private fun testFragment(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container,ProfileFragment())
            .commit()
    }

//TODO 47 - Add onCreateOptionMenu method to return main_menu resource
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu,menu)
        return true
    }

    //TODO 47 - Add onOptionItemSelected method to return logout ID from main_menu resource
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.logout -> {
                sessionManager.logout()
                return true
            }
        }
        return super.onOptionsItemSelected(item)

    }
}
