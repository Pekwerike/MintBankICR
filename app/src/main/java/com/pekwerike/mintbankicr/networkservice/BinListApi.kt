package com.pekwerike.mintbankicr.networkservice

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class BinListApi @Inject constructor(private val retrofit: Retrofit){
    fun binListApiService(): BinListApiService{
        return retrofit.create(BinListApiService::class.java)
    }
}