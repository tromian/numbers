package com.tromian.test.numberfacts.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [NumberEntity::class],
    version = 1,
    exportSchema = false
)
abstract class NumbersDB : RoomDatabase() {

    abstract fun numbersDao() : NumbersDao

    companion object {
        private const val DATABASE_NAME = "numbers.db"

        private var INSTANCE: NumbersDB? = null

        private val lock = Any()

        fun getInstance(appContext: Context): NumbersDB {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        appContext,
                        NumbersDB::class.java, DATABASE_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                }
                return INSTANCE!!
            }
        }

    }
}