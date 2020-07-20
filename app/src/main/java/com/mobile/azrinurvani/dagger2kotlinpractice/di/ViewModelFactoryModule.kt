package com.mobile.azrinurvani.dagger2kotlinpractice.di

import androidx.lifecycle.ViewModelProvider
import com.mobile.azrinurvani.dagger2kotlinpractice.viewmodels.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

//TODO 7 - Create ViewModelFactoryModule for bind ViewModelProviderFactory class
@Module
abstract class ViewModelFactoryModule {

    @Binds
    internal abstract fun bindViewModelFactory(modelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory

}