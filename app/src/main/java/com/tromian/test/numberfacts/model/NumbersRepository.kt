package com.tromian.test.numberfacts.model

interface NumbersRepository {
    suspend fun getFactAboutNumber(number: String) : Number?
    suspend fun getFactAboutRandomNumber() : Number?
    fun getSearchHistory() : List<Number>
    fun saveNumberToDB(number: Number)
}