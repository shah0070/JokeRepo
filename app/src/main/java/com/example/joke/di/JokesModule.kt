package com.example.joke.di

import android.content.Context
import com.example.joke.BuildConfig
import com.example.joke.data.JokeAPI
import com.example.joke.data.JokeDBRepoImpl
import com.example.joke.data.JokeRepoImpl
import com.example.joke.domain.JokeDBRepository
import com.example.joke.domain.JokeRepository
import com.example.joke.domain.JokeUseCase
import com.example.joke.helper.BASE_URL
import com.example.joke.room.JokeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Created by Md Shah Hussain on 02/09/23.
 */
@Module
@InstallIn(ApplicationComponent::class)
object JokesModule {
    @Provides
    fun provideJokeRepository(api: JokeAPI): JokeRepository {
        return JokeRepoImpl(api)
    }

    @Provides
    fun provideJokeDBRepository(jokeDatabase: JokeDatabase): JokeDBRepository {
        return JokeDBRepoImpl(jokeDatabase)
    }

    @Provides
    fun provideJokeUseCase(
        jokeRepository: JokeRepository,
        jokeDBRepository: JokeDBRepository
    ): JokeUseCase {
        return JokeUseCase(jokeRepository, jokeDBRepository)
    }

    @Singleton
    @Provides
    fun provideRoomDbInstance(@ApplicationContext appContext: Context): JokeDatabase {
        return JokeDatabase.getDatabaseClient(appContext)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            okHttpClient.addNetworkInterceptor(
                HttpLoggingInterceptor().setLevel(
                    HttpLoggingInterceptor.Level.BODY
                )
            )
        }
        return okHttpClient.build()
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): JokeAPI {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        return retrofit.create(JokeAPI::class.java)
    }
}