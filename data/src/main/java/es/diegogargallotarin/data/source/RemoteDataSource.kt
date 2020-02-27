package es.diegogargallotarin.data.source

import es.diegogargallotarin.domain.Dish

interface RemoteDataSource {
    suspend fun getAllDishes(): List<Dish>
}