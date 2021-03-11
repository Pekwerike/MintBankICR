package com.pekwerike.mintbankicr.di

import com.pekwerike.mintbankicr.repository.implementation.MainRepository
import com.pekwerike.mintbankicr.repository.repositoryinterface.BinListNetworkRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

const val BASE_URL = "https://lookup.binlist.net"
@InstallIn(ViewModelComponent::class)
@Module
class NetworkDependencyInjectionModule {

    @Provides
    @Singleton
    fun getMoshi(): Moshi{
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun getRetrofit(moshi : Moshi) : Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .build()
    }
}
