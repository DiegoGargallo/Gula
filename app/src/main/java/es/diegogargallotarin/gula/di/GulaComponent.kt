package es.diegogargallotarin.gula.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import es.diegogargallotarin.gula.ui.detail.DetailViewModel
import es.diegogargallotarin.gula.ui.main.MainViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class, UseCaseModule::class, ViewModelsModule::class])
interface GulaComponent {

    val mainViewModel: MainViewModel
    val detaiViewModel: DetailViewModel

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): GulaComponent
    }
}