package com.tromian.test.numberfacts.di

import android.app.Application
import android.util.Log
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.tromian.test.numberfacts.AppConstants
import com.tromian.test.numberfacts.BuildConfig
import com.tromian.test.numberfacts.data.NumbersRepositoryImpl
import com.tromian.test.numberfacts.data.db.NumbersDB
import com.tromian.test.numberfacts.data.network.NumbersApi
import com.tromian.test.numberfacts.model.NumbersRepository
import com.tromian.test.numberfacts.presentation.MainActivity
import com.tromian.test.numberfacts.presentation.main.MainScreenFragment
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {

    fun inject(fragment: MainScreenFragment)

    @Component.Factory
    interface Builder {

        fun create(
            @BindsInstance
            appContext: Application
        ): AppComponent

    }
}


@Module(includes = [NetworkModule::class, LocalDBModule::class])
class AppModule {

    @Provides
    fun provideRepository(
        localDB: NumbersDB,
        numbersApi: NumbersApi,
        appContext: Application,
    ): NumbersRepository {
        return NumbersRepositoryImpl(localDB, numbersApi, appContext)
    }
}

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideWeatherService(): NumbersApi {
        val authInterceptor = Interceptor { chain ->
            val newUrl = chain.request().url
                .newBuilder()
                .build()

            val newRequest = chain.request()
                .newBuilder()
                .url(newUrl)
                .build()

            chain.proceed(newRequest)

        }


        val client = OkHttpClient().newBuilder()
            .addInterceptor(authInterceptor)
            .apply {
                if (BuildConfig.DEBUG){
                    val logger = HttpLoggingInterceptor(
                        HttpLoggingInterceptor.Logger { Log.d("API", it) }
                    ).apply {
                        this.level = HttpLoggingInterceptor.Level.BASIC
                    }
                    this.addInterceptor(logger)

                }
            }
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(AppConstants.BASE_URL_NUMBERS_API)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        return retrofit.create(NumbersApi::class.java)
    }
}

@Module
class LocalDBModule {
    @Provides
    @Singleton
    fun provideLocalDB(appContext: Application): NumbersDB {
        return NumbersDB.getInstance(appContext)
    }

}