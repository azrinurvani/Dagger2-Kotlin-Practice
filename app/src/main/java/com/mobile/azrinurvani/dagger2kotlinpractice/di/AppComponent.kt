package com.mobile.azrinurvani.dagger2kotlinpractice.di

import android.app.Application
import com.mobile.azrinurvani.dagger2kotlinpractice.BaseApplication
import com.mobile.azrinurvani.dagger2kotlinpractice.SessionManager
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
//TODO 3 - Create AppComponent
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuildersModule::class,
        //TODO 13 - Add Module at Component
        AppModule::class,
        ViewModelFactoryModule::class
    ]
)
interface AppComponent : AndroidInjector<BaseApplication>{

    //TODO 35 - Create sessionManager() function in AppComponent
    fun sessionManager(): SessionManager

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application (application : Application) : Builder

        fun build() : AppComponent
    }
}