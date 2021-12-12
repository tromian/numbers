package com.tromian.test.numberfacts.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NumbersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveNumber(numberEntity: NumberEntity)

    @Query("select * from numbers order by id desc")
    fun getSearchHistory() : List<NumberEntity>

    @Query("select count(*) from numbers")
    fun checkColumnCount() : Int

}