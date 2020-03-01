package es.diegogargallotarin.gula.di

import dagger.Module
import dagger.Provides
import es.diegogargallotarin.data.repository.GulaRepository
import es.diegogargallotarin.data.source.LocalDataSource
import es.diegogargallotarin.data.source.RemoteDataSource

@Module
class DataModule {

    @Provides
    fun dishesRepositoryProvider(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ) = GulaRepository(localDataSource, remoteDataSource)
}