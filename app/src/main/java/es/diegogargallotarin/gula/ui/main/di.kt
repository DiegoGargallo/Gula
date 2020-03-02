package es.diegogargallotarin.gula.ui.main

import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import es.diegogargallotarin.data.repository.GulaRepository
import es.diegogargallotarin.usecases.GetDishes


@Module
class MainActivityModule {

    @Provides
    fun mainViewModelProvider(getDishes: GetDishes) = MainViewModel(getDishes)

    @Provides
    fun getPopularMoviesProvider(gulaRepository: GulaRepository) =
        GetDishes(gulaRepository)
}

@Subcomponent(modules = [(MainActivityModule::class)])
interface MainActivityComponent {
    val mainViewModel: MainViewModel
}