package es.diegogargallotarin.gula.data.database

import es.diegogargallotarin.data.source.LocalDataSource
import es.diegogargallotarin.domain.Contribution
import es.diegogargallotarin.domain.Dish
import es.diegogargallotarin.gula.data.toDomainContribution
import es.diegogargallotarin.gula.data.toDomainDish
import es.diegogargallotarin.gula.data.toRoomContribution
import es.diegogargallotarin.gula.data.toRoomDish
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDataSource (db: GulaDatabase) : LocalDataSource {

    private val dishDao = db.dishDao()
    private val restaurantDao = db.restaurantDao()
    private val contributionDao = db.contributionDao()

    override suspend fun isEmpty(): Boolean =
        withContext(Dispatchers.IO) { dishDao.dishCount() <= 0 }

    override suspend fun saveDishes(dishes: List<Dish>) {
        withContext(Dispatchers.IO) { dishDao.insertDishes(dishes.map { it.toRoomDish() }) }
    }

    override suspend fun saveContributions(contributions: List<Contribution>) {
        withContext(Dispatchers.IO) { contributionDao.insertContributions(contributions.map { it.toRoomContribution() }) }
    }

    override suspend fun getAllDishes(): List<Dish> = withContext(Dispatchers.IO) {
        dishDao.getAll().map { it.toDomainDish() }
    }

    override suspend fun findByName(name: String): Dish = withContext(Dispatchers.IO) {
        dishDao.findByName(name).toDomainDish()
    }

    override suspend fun isDishContributionsEmpty(name: String): Boolean = withContext(Dispatchers.IO) {
        contributionDao.contributionCount(name) <= 0
    }

    override suspend fun getContributionsByDishName(name: String): List<Contribution> = withContext(Dispatchers.IO) {
        contributionDao.findContributionsByDishName(name).map { it.toDomainContribution() }.toMutableList()
    }

    override suspend fun update(dish: Dish) {
        withContext(Dispatchers.IO) { dishDao.updateDish(dish.toRoomDish()) }
    }
}