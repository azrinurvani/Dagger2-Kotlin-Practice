package com.mobile.azrinurvani.dagger2kotlinpractice.di

import com.mobile.azrinurvani.dagger2kotlinpractice.ui.main.MainActivity
import com.mobile.azrinurvani.dagger2kotlinpractice.di.auth.AuthModule
import com.mobile.azrinurvani.dagger2kotlinpractice.ui.auth.AuthActivity
import com.mobile.azrinurvani.dagger2kotlinpractice.di.auth.AuthViewModelModule
import com.mobile.azrinurvani.dagger2kotlinpractice.di.main.MainFragmentBuildersModule
import com.mobile.azrinurvani.dagger2kotlinpractice.di.main.MainModule
import com.mobile.azrinurvani.dagger2kotlinpractice.di.main.MainViewModelsModule
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
    @ContributesAndroidInjector(modules = [
        //TODO 52 - Register MainFragmentBuildersModule in MainActivityBuilder
        MainFragmentBuildersModule::class,
        //TODO 55 - Register MainViewModel on contribueMainActivity as subcomponent
        MainViewModelsModule::class,
        //TODO 65 - Add MainModule to contributeMainActivity
        MainModule::class
    ])
    abstract fun contributeMainActivity() : MainActivity

}

