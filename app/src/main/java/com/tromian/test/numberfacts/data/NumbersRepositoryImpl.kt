package com.tromian.test.numberfacts.data

import android.content.Context
import android.util.Log
import com.tromian.test.numberfacts.data.db.NumbersDB
import com.tromian.test.numberfacts.data.network.NetworkConnection
import com.tromian.test.numberfacts.data.network.NumbersApi
import com.tromian.test.numberfacts.model.Number
import com.tromian.test.numberfacts.model.NumbersRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class NumbersRepositoryImpl @Inject constructor(
    val db: NumbersDB,
    val api: NumbersApi,
    val context: Context
) : NumbersRepository {

    override suspend fun getFactAboutNumber(number: String): Number? {
        return if (NetworkConnection.isNetworkAvailable(context)){
            try {
                api.getFactAboutNumber(number).toDomain()
            } catch (e: HttpException) {
                e.message?.let { Log.e("http", it) }
                null
            } catch (e: IOException) {
                e.message?.let { Log.e("IOException", it) }
                null
            }
        }else null
    }

    override suspend fun getFactAboutRandomNumber(): Number? {
        return if (NetworkConnection.isNetworkAvailable(context)){
            try {
                api.getFactAboutRandomNumber().toDomain()
            } catch (e: HttpException) {
                e.message?.let { Log.e("http", it) }
                null
            } catch (e: IOException) {
                e.message?.let { Log.e("IOException", it) }
                null
            }
        }else null
    }

    override fun getSearchHistory(): List<Number> {
        return if (db.numbersDao().checkColumnCount() > 0){
            db.numbersDao().getSearchHistory().map {
                it.toDomain()
            }
        }else emptyList()
    }

    override fun saveNumberToDB(number: Number) {
        db.numbersDao().saveNumber(number.toEntity())
    }

}