package com.mobile.azrinurvani.dagger2kotlinpractice.ui.main

import androidx.annotation.NonNull
import androidx.annotation.Nullable

//TODO 73 - Create Resource.class to manage posts data when retrieve from API
class Resource<T>(
    val status: Status?,
    val data: T?,
    val message: String?)
{

    enum class Status {
        SUCCESS, ERROR, LOADING
    }

    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(@NonNull msg: String?): Resource<T> {
            return Resource(Status.ERROR, null, msg)
        }

        fun <T> loading(@Nullable data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }

}