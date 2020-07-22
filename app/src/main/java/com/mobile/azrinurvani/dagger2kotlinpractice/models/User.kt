package com.mobile.azrinurvani.dagger2kotlinpractice.models

import com.google.gson.annotations.SerializedName

// TODO 16 - Add User DTO Class for models
data class User (
    @field:SerializedName("id")
    val id : Int? = null,

    @field:SerializedName("username")
    val username : String? = null,

    @field:SerializedName("email")
    val email : String? = null,

    @field:SerializedName("website")
    val website : String? = null

)