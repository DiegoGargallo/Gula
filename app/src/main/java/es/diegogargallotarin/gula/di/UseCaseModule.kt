package es.diegogargallotarin.gula.di

import dagger.Module
import dagger.Provides
import es.diegogargallotarin.data.repository.GulaRepository
import es.diegogargallotarin.usecases.FindContributionsByDishName
import es.diegogargallotarin.usecases.FindDishByName
import es.diegogargallotarin.usecases.GetDishes
import es.diegogargallotarin.usecases.ToggleDishFavorite

@Module
class UseCaseModule {

    @Provides
    fun getAllDishesProvider(gulaRepository: GulaRepository) =
        GetDishes(gulaRepository)

    @Provides
    fun findDishByNameProvider(gulaRepository: GulaRepository) = FindDishByName(gulaRepository)

    @Provides
    fun findContributionsByDishName(gulaRepository: GulaRepository) = FindContributionsByDishName(gulaRepository)

    @Provides
    fun toggleMovieFavoriteProvider(gulaRepository: GulaRepository) =
        ToggleDishFavorite(gulaRepository)
}