package com.mobile.azrinurvani.dagger2kotlinpractice.di.main

import androidx.lifecycle.ViewModel
import com.mobile.azrinurvani.dagger2kotlinpractice.di.ViewModelKey
import com.mobile.azrinurvani.dagger2kotlinpractice.ui.main.posts.PostsViewModel
import com.mobile.azrinurvani.dagger2kotlinpractice.ui.main.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

//TODO 54 - Create MainViewModelsModule for bind ProfileViewModel and PostViewModel
@Module
abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(viewModel: ProfileViewModel): ViewModel

    //TODO 69 - Bind PostsViewModel
    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel::class)
    abstract fun bindPostsViewModel(viewModel: PostsViewModel): ViewModel
}