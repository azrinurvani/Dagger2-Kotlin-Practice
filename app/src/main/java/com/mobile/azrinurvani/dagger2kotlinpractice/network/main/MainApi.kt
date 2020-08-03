package com.mobile.azrinurvani.dagger2kotlinpractice.network.main

import com.mobile.azrinurvani.dagger2kotlinpractice.models.Post
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

//TODO 63 - Create MainApi
interface MainApi {

    @GET("posts")
    fun getPostsFromUser(
        @Query("userId") id : Int
    ) : Flowable<List<Post>>
}