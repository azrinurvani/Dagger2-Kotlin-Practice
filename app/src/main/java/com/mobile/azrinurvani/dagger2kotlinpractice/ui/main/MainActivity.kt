package com.mobile.azrinurvani.dagger2kotlinpractice.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import com.mobile.azrinurvani.dagger2kotlinpractice.BaseActivity
import com.mobile.azrinurvani.dagger2kotlinpractice.R
import com.mobile.azrinurvani.dagger2kotlinpractice.ui.main.posts.PostsFragment
import com.mobile.azrinurvani.dagger2kotlinpractice.ui.main.profile.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener{ //TODO 90 - Implements NavigationSelectedListener

    companion object{
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

//    TODO 94 - Create initNavController
    private fun init(){
        val navController = Navigation.findNavController(this,R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this,navController,drawer_layout)
        NavigationUI.setupWithNavController(nav_view,navController)
        nav_view.setNavigationItemSelectedListener(this)
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
            //TODO 97 - Add this for fix drawer can't closed
            android.R.id.home->{
                return if (drawer_layout.isDrawerOpen(GravityCompat.START)){
                    drawer_layout.closeDrawer(GravityCompat.START)
                    true
                }else{
                    false
                }
            }
        }
        return super.onOptionsItemSelected(item)

    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        //TODO 91 - Create switch statement for checking id behavior action
        when (menuItem.itemId){
            R.id.nav_profile ->{

                //TODO 98 - Add navOptions for clear backstack
                val navOptions = NavOptions.Builder()
                    .setPopUpTo(R.id.main,true)
                    .build()

                //TODO 95 - Call findNavController to navigate the fragment on MainActivity
                Navigation.findNavController(this,R.id.nav_host_fragment).navigate(R.id.profileScreen,null,navOptions)
                //it is same with
//                supportFragmentManager.beginTransaction()
//                    .replace(R.id.main_container,PostsFragment())
//                    .commit()
            }
            R.id.nav_posts->{

                //TODO 100 - Handle backstack issue to check current destination
                if (isValidDestination(R.id.postsScreen)){
                    Navigation.findNavController(this,R.id.nav_host_fragment).navigate(R.id.postsScreen)
                }

            }
        }
        menuItem.isChecked = true
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
    //TODO 99 - Create isValidDestination() method for keep current destination
    private fun isValidDestination(destination : Int): Boolean{
        return destination != Navigation.findNavController(this,R.id.nav_host_fragment).currentDestination?.id
    }

    //TODO 96 - Called onSupportNavigateUp to fix Hamburger and Back icon can't to click
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(Navigation.findNavController(this,R.id.nav_host_fragment),drawer_layout)
    }
}
