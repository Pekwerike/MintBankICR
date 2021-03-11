package com.pekwerike.mintbankicr.network

import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class BinListApi @Inject constructor(private val retrofit: Retrofit){
    fun binListApiService(): BinListApiService{
        return retrofit.create(BinListApiService::class.java)
    }
}