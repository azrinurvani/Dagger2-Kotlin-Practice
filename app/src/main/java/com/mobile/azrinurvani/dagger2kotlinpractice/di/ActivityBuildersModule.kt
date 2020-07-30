package com.mobile.azrinurvani.dagger2kotlinpractice.di

import com.mobile.azrinurvani.dagger2kotlinpractice.ui.main.MainActivity
import com.mobile.azrinurvani.dagger2kotlinpractice.di.auth.AuthModule
import com.mobile.azrinurvani.dagger2kotlinpractice.ui.auth.AuthActivity
import com.mobile.azrinurvani.dagger2kotlinpractice.di.auth.AuthViewModelModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


//TODO 4 - Create ActivityBuilderModule for declare your Activity or Fragment
@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules= [
        //TODO 14 - Dont forget add AuthViewModelModule at contributeActivity Module
        AuthViewModelModule::class,
        AuthModule::class
    ])
    abstract fun contributeAuthActivity() : AuthActivity

    //TODO 45- Add contributeAndroidInjector for MainActivity
    @ContributesAndroidInjector
    abstract fun contributeMainActivity() : MainActivity

}

