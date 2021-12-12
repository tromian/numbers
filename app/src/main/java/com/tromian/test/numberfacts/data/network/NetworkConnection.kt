package com.tromian.test.numberfacts.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

object NetworkConnection {
    fun isNetworkAvailable(context: Context): Boolean {
        val conManager = context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

            val activeNetwork = conManager.activeNetwork ?: return false
            val capabilities = conManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }

    }
}