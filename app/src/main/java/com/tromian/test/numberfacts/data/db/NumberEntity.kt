package com.tromian.test.numberfacts.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "numbers")
data class NumberEntity(
    @ColumnInfo(name = "number")
    val number: Int,
    @ColumnInfo(name = "text")
    val text: String
){
    @PrimaryKey( autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Int = 0
}
