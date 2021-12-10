package com.tromian.test.numberfacts.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Number(
    val number: Int,
    val text: String
) : Parcelable
