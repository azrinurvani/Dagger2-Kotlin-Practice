package com.mobile.azrinurvani.dagger2kotlinpractice.di.auth

import androidx.lifecycle.ViewModel
import com.mobile.azrinurvani.dagger2kotlinpractice.di.ViewModelKey
import com.mobile.azrinurvani.dagger2kotlinpractice.ui.auth.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

//TODO 12 -  Create AuthViewModelModule to bind ViewModel using ViewModelKey
@Module
abstract class AuthViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(viewModel: AuthViewModel): ViewModel

    //You can bind other ViewModel its here

}