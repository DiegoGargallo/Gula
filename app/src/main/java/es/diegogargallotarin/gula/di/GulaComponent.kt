package es.diegogargallotarin.gula.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import es.diegogargallotarin.gula.ui.detail.DetailActivityComponent
import es.diegogargallotarin.gula.ui.detail.DetailActivityModule
import es.diegogargallotarin.gula.ui.main.MainActivityComponent
import es.diegogargallotarin.gula.ui.main.MainActivityModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class])
interface GulaComponent {

    fun plus(module: MainActivityModule): MainActivityComponent
    fun plus(module: DetailActivityModule) : DetailActivityComponent

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): GulaComponent
    }
}