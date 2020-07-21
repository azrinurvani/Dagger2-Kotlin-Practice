package com.mobile.azrinurvani.dagger2kotlinpractice.di.auth

import com.mobile.azrinurvani.dagger2kotlinpractice.network.AuthApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit


@Module
class AuthModule {
    @Provides
    open fun provideAuthApi(retrofit: Retrofit) : AuthApi{
        return retrofit.create(AuthApi::class.java)
    }
}