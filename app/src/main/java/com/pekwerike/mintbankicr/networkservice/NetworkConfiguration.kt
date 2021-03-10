package com.pekwerike.mintbankicr.networkservice

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


const val BASE_URL = "https://lookup.binlist.net"


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


object BinListApi {
    val binListApiService: BinListApiService by lazy {
        retrofit.create(BinListApiService::class.java)
    }
}

