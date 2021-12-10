package com.tromian.test.numberfacts.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "numbers")
data class NumberEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private val id : Int = 0,
    @ColumnInfo(name = "number")
    val number: Int,
    @ColumnInfo(name = "text")
    val text: String
)
