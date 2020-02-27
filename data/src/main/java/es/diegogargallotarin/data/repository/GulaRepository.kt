package es.diegogargallotarin.data.repository

import es.diegogargallotarin.data.source.LocalDataSource
import es.diegogargallotarin.data.source.RemoteDataSource
import es.diegogargallotarin.domain.Contribution
import es.diegogargallotarin.domain.Dish

public class GulaRepository(val localDataSource: LocalDataSource,
                            val remoteDataSource: RemoteDataSource
) {

    suspend fun getAllDishes() :  List<Dish> {

        if (localDataSource.isEmpty()) {
            val dishes =  remoteDataSource.getAllDishes()
            localDataSource.saveDishes(dishes)
        }
        return localDataSource.getAllDishes()
    }

    suspend fun findByName(name: String): Dish = localDataSource.findByName(name)

    suspend fun findContributionsByDishName(name: String): List<Contribution> = localDataSource.findContributionsByDishName(name)

    suspend fun update(movie: Dish) = localDataSource.update(movie)
}

