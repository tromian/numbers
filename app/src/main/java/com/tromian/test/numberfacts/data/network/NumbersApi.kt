package com.tromian.test.numberfacts.data.network

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path


interface NumbersApi {
    @Headers("Content-Type: application/json")
    @GET("{number}?json")
    suspend fun getFactAboutNumber(@Path("number") number: String) : NumberResponse

    @Headers("Content-Type: application/json")
    @GET("random/math")
    suspend fun getFactAboutRandomNumber() : NumberResponse

}