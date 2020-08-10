package com.mobile.azrinurvani.dagger2kotlinpractice.di.auth


import com.mobile.azrinurvani.dagger2kotlinpractice.models.User
import com.mobile.azrinurvani.dagger2kotlinpractice.network.auth.AuthApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

import javax.inject.Singleton


@Module
class AuthModule {

    //TODO 105 - Add @AuthScope
    @AuthScope
    @Provides
    open fun provideAuthApi(retrofit: Retrofit) : AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    //TODO 107 - This is just for test for scoping singleton
//    @Singleton
//    @Provides
//    @Named("auth_user")
//    open fun someUser() : User{
//        return User()
//    }
}