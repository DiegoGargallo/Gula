package es.diegogargallotarin.data.repository

import es.diegogargallotarin.data.source.LocalDataSource
import es.diegogargallotarin.data.source.RemoteDataSource
import es.diegogargallotarin.domain.Contribution
import es.diegogargallotarin.domain.Dish

class GulaRepository(val localDataSource: LocalDataSource,
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

    suspend fun getContributionsByDishName(name: String): List<Contribution> {
        if (localDataSource.isDishContributionsEmpty(name)) {
            val contributions =  remoteDataSource.getContributionsByDishName(name)
            localDataSource.saveContributions(contributions)
        }
        return localDataSource.getContributionsByDishName(name)
    }

    suspend fun update(movie: Dish) = localDataSource.update(movie)
}

