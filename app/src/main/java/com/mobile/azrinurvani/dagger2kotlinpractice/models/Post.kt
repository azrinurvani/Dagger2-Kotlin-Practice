package com.mobile.azrinurvani.dagger2kotlinpractice.models

import com.google.gson.annotations.SerializedName

//TODO 62 - Create Post DTO
data class Post(
    @field:SerializedName("userId")
    var userId : Int? = null,
    @field:SerializedName("id")
    var id : Int? = null,
    @field:SerializedName("title")
    var title : Int? = null,
    @field:SerializedName("body")
    var body : Int? = null )