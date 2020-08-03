package com.mobile.azrinurvani.dagger2kotlinpractice.network.auth

import com.mobile.azrinurvani.dagger2kotlinpractice.models.User
import io.reactivex.Flowable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthApi {

//    TODO 15 - Add endpoint for get User by ID
    @GET("users/{id}")
    fun getUser(
        @Path("id") id : Int
    ) : Flowable<User>

}