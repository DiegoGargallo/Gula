package es.diegogargallotarin.data.source

import es.diegogargallotarin.domain.Contribution
import es.diegogargallotarin.domain.Dish

interface RemoteDataSource {
    suspend fun getAllDishes(): List<Dish>
    suspend fun getContributionsByDishName(dishName: String): List<Contribution>
}