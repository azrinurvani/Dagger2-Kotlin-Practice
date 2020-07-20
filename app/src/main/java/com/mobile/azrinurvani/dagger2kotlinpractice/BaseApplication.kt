package com.mobile.azrinurvani.dagger2kotlinpractice

import com.mobile.azrinurvani.dagger2kotlinpractice.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

//TODO 2 - Create Base Application
class BaseApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication>? {
        return DaggerAppComponent.builder().application(this).build()
    }

}