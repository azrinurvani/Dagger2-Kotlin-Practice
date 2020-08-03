package com.mobile.azrinurvani.dagger2kotlinpractice.di.main

import com.mobile.azrinurvani.dagger2kotlinpractice.network.main.MainApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

//TODO 64 - Create MainModule to provide module for MainActivity
@Module
class MainModule {

    @Provides
    open fun provideMainApi(retrofit: Retrofit) : MainApi{
        return retrofit.create(MainApi::class.java)
    }
}