package com.tromian.test.numberfacts.data

import android.content.Context
import com.tromian.test.numberfacts.data.db.NumbersDB
import com.tromian.test.numberfacts.data.network.NumbersApi
import com.tromian.test.numberfacts.model.Number
import com.tromian.test.numberfacts.model.NumbersRepository
import javax.inject.Inject

class NumbersRepositoryImpl @Inject constructor(
    db: NumbersDB,
    api: NumbersApi,
    context: Context
) : NumbersRepository {

    override fun getFactAboutNumber(number: Int): Number {
        TODO("Not yet implemented")
    }

    override fun getFactAboutNumberFromDB(number: Number): Number {
        TODO("Not yet implemented")
    }

    override fun getFactAboutRandomNumber(): Number {
        TODO("Not yet implemented")
    }

    override fun getSearchHistory(): List<Number> {
        TODO("Not yet implemented")
    }

    override fun saveNumberToDB(number: Number) {
        TODO("Not yet implemented")
    }

}