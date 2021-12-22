package com.example.marvelapp.framework.di

import com.example.core.data.repository.CharactersRemoteDataSource
import com.example.core.data.repository.CharactersRepository
import com.example.marvelapp.framework.CharactersRepositoryImpl
import com.example.marvelapp.framework.network.remote.RetrofitCharacterDataSource
import com.example.marvelapp.framework.network.response.DataWrapperResponse
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    //Instruindo o Hilt para quando alguém depender da Interface CharactersRepository injetar o CharactersRepositoryImpl
    @Binds
    fun bindCharacterRepository(repository: CharactersRepositoryImpl) : CharactersRepository

    //Instruindo o Hilt para quando alguém depender da Interface CharactersRemoteDataSource injetar o RetrofitCharacterDataSource
    @Binds
    fun bindRemoteDataSource(dataSource: RetrofitCharacterDataSource) : CharactersRemoteDataSource<DataWrapperResponse>
}