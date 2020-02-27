package es.diegogargallotarin.data.source

import es.diegogargallotarin.domain.Dish
import es.diegogargallotarin.domain.Contribution

interface LocalDataSource{
    suspend fun isEmpty(): Boolean
    suspend fun saveDishes(dishes: List<Dish>)
    suspend fun getAllDishes(): List<Dish>
    suspend fun findByName(name: String): Dish
    suspend fun findContributionsByDishName(name: String): List<Contribution>
    suspend fun update(dish: Dish)
}