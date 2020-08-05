package com.mobile.azrinurvani.dagger2kotlinpractice.models

import com.google.gson.annotations.SerializedName

//TODO 62 - Create Post DTO
data class Post(
    @field:SerializedName("id")
    var id : Int? = null,
    @field:SerializedName("title")
    val title : String? = null,
    @field:SerializedName("body")
    val body : String? = null,
    @field:SerializedName("userId")
    val userId : Int? = null )