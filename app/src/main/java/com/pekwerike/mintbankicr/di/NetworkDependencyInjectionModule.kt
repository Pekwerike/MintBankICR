package com.pekwerike.mintbankicr.di

import com.pekwerike.mintbankicr.repository.implementation.MainRepository
import com.pekwerike.mintbankicr.repository.repositoryinterface.BinListNetworkRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ActivityComponent::class)
@Module
class NetworkDependencyInjectionModule {
}

@InstallIn(ViewModelComponent::class)
@Module
abstract class RepositoryDependencyInjectionModule {

    @Binds
    abstract fun bindBinListNetworkRepositoryInterface(mainRepository: MainRepository): BinListNetworkRepository
}