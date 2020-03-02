package es.diegogargallotarin.gula.ui.detail

import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import es.diegogargallotarin.data.repository.GulaRepository
import es.diegogargallotarin.usecases.FindContributionsByDishName
import es.diegogargallotarin.usecases.FindDishByName
import es.diegogargallotarin.usecases.ToggleDishFavorite


@Module
class DetailActivityModule(private val dishName: String) {

    @Provides
    fun detailViewModelProvider(
        findDishByName: FindDishByName,
        findContributionsByDishName: FindContributionsByDishName,
        toggleDishFavorite: ToggleDishFavorite
    ): DetailViewModel {
        return DetailViewModel(dishName,findDishByName, findContributionsByDishName, toggleDishFavorite)
    }

    @Provides
    fun findDishByNameProvider(gulaRepository: GulaRepository) = FindDishByName(gulaRepository)

    @Provides
    fun findContributionsByDishNameProvider(gulaRepository: GulaRepository) = FindContributionsByDishName(gulaRepository)

    @Provides
    fun toggleMovieFavoriteProvider(gulaRepository: GulaRepository) =
        ToggleDishFavorite(gulaRepository)
}

@Subcomponent(modules = [(DetailActivityModule::class)])
interface DetailActivityComponent {
    val detaiViewModel: DetailViewModel
}