package com.mobile.azrinurvani.dagger2kotlinpractice.di.auth

import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Scope

//TODO 101 - Create annotation class for Auth Custom Scope
@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
annotation class AuthScope