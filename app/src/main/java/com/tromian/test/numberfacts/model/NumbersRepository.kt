package com.tromian.test.numberfacts.model

interface NumbersRepository {
    fun getFactAboutNumber(number: Int) : Number
    fun getFactAboutNumberFromDB(number: Number) : Number
    fun getFactAboutRandomNumber() : Number
    fun getSearchHistory() : List<Number>
    fun saveNumberToDB(number: Number)
}