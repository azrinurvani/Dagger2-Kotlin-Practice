package com.mobile.azrinurvani.dagger2kotlinpractice.di

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.mobile.azrinurvani.dagger2kotlinpractice.R
import com.mobile.azrinurvani.dagger2kotlinpractice.models.User
import com.mobile.azrinurvani.dagger2kotlinpractice.util.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton



//TODO 5 - Create AppModule for provide object when you using in Application Scope
@Module
class AppModule {

    @Singleton
    @Provides
    open fun provideRetrofitInstance() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
                //TODO 14 - Add CallADapterFactory for RxJava2
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }



    @Singleton
    @Provides
    open fun provideRequestOption() : RequestOptions{
        return RequestOptions()
            .placeholder(R.drawable.white_background)
            .error(R.drawable.white_background)
    }


    //parameter requestOptions in provideGlideInstance available because before i'am providing RequestOption with @Provides annotation
    @Singleton
    @Provides
    open fun provideGlideInstance(application : Application, requestOptions : RequestOptions) : RequestManager{
        return Glide.with(application)
            .setDefaultRequestOptions(requestOptions)
    }
    @Singleton
    @Provides
    open fun provideAppDrawable(application: Application) : Drawable {
        return ContextCompat.getDrawable(application,R.drawable.logo)!!
    }

//    //TODO 107 - This is just for test for scoping singleton
//    @Singleton
//    @Provides
//    @Named("app_user")
//    open fun someUser() : User {
//        return User()
//    }


}