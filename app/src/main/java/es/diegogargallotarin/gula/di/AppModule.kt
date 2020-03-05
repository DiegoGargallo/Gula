package es.diegogargallotarin.gula.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import es.diegogargallotarin.data.source.LocalDataSource
import es.diegogargallotarin.data.source.RemoteDataSource
import es.diegogargallotarin.gula.data.database.GulaDatabase
import es.diegogargallotarin.gula.data.database.RoomDataSource
import es.diegogargallotarin.gula.data.server.FirebaseDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun databaseProvider(app: Application) = Room.databaseBuilder(
        app,
        GulaDatabase::class.java,
        "movie-db"
    ).build()

    @Provides
    fun localDataSourceProvider(db: GulaDatabase): LocalDataSource = RoomDataSource(db)

    @Provides
    fun remoteDataSourceProvider(): RemoteDataSource = FirebaseDataSource()

    @Provides
    fun coroutineDispatcherProvider(): CoroutineDispatcher = Dispatchers.Main
}