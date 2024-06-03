package com.jorgesanaguaray.consumeapijetpackcomposetutorial.di

import com.google.gson.GsonBuilder
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote.VideoApi
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.GetVideosUseCase
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.repo.VideoRepository
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Jorge Sanaguaray
 */

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val gson = GsonBuilder().setLenient().create()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideVideoApi(retrofit: Retrofit): VideoApi{
        return retrofit.create(VideoApi::class.java)
    }


    @Singleton
    @Provides
    fun provideGetVideosUseCase(videoRepository : VideoRepository): GetVideosUseCase {
        return GetVideosUseCase(videoRepository)
    }





}