package es.diegogargallotarin.gula.di

import dagger.Module
import dagger.Provides
import es.diegogargallotarin.gula.ui.detail.DetailViewModel
import es.diegogargallotarin.gula.ui.main.MainViewModel
import es.diegogargallotarin.usecases.FindContributionsByDishName
import es.diegogargallotarin.usecases.FindDishByName
import es.diegogargallotarin.usecases.GetDishes
import es.diegogargallotarin.usecases.ToggleDishFavorite

@Module
class ViewModelsModule {

    @Provides
    fun mainViewModelProvider(getDishes: GetDishes) = MainViewModel(getDishes)

    @Provides
    fun detailViewModelProvider(
        findMovieById: FindDishByName,
        findContributionsByDishName: FindContributionsByDishName,
        toggleMovieFavorite: ToggleDishFavorite
    ): DetailViewModel {
        // TODO the id needs to be provided. We'll see it with scoped graphs
        return DetailViewModel("", findMovieById, findContributionsByDishName, toggleMovieFavorite)
    }
}