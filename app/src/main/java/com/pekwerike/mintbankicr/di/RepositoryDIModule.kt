package com.pekwerike.mintbankicr.di

import com.pekwerike.mintbankicr.repository.implementation.MainRepository
import com.pekwerike.mintbankicr.repository.repositoryinterface.BinListNetworkRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class RepositoryDIModule {
    @Binds
    abstract fun bindBinListNetworkRepositoryInterface(mainRepository: MainRepository): BinListNetworkRepository
}