package com.tromian.test.numberfacts.data.network

import com.google.gson.annotations.SerializedName


data class NumberResponse(
    @SerializedName("found")
    val found: Boolean,
    @SerializedName("number")
    val number: Int,
    @SerializedName("text")
    val text: String,
    @SerializedName("type")
    val type: String
)