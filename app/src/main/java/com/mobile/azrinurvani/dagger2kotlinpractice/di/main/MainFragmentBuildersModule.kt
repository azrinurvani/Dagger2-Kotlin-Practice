package com.mobile.azrinurvani.dagger2kotlinpractice.di.main

import com.mobile.azrinurvani.dagger2kotlinpractice.ui.main.posts.PostsFragment
import com.mobile.azrinurvani.dagger2kotlinpractice.ui.main.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

//TODO 51 - Create MainFragmentBuildersModule to assign Profile and Post Fragment as SubComponent from MainActivity
@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeProfileFragment() : ProfileFragment

    //TODO 70 - Add contributePostsFragment
    @ContributesAndroidInjector
    abstract fun contributePostsFragment() : PostsFragment

}