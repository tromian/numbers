package com.tromian.test.numberfacts.data.network

import retrofit2.http.GET
import retrofit2.http.Path


interface NumbersApi {

    @GET("number/{number}")
    fun getFactAboutNumber(@Path("number") number: Int) : NumberResponse

    @GET("number/random/math")
    fun getFactAboutRandomNumber() : NumberResponse

}