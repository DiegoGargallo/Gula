package es.diegogargallotarin.gula.model.database

import es.diegogargallotarin.data.source.LocalDataSource
import es.diegogargallotarin.domain.Contribution
import es.diegogargallotarin.domain.Dish
import es.diegogargallotarin.gula.model.toDomainContribution
import es.diegogargallotarin.gula.model.toDomainDish
import es.diegogargallotarin.gula.model.toRoomDish
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

    override suspend fun getAllDishes(): List<Dish> = withContext(Dispatchers.IO) {
        dishDao.getAll().map { it.toDomainDish() }
    }

    override suspend fun findByName(name: String): Dish = withContext(Dispatchers.IO) {
        dishDao.findByName(name).toDomainDish()
    }

    override suspend fun findContributionsByDishName(name: String): List<Contribution> = withContext(Dispatchers.IO) {
        contributionDao.findContributionsByDishName(name).map { it.toDomainContribution() }.toMutableList();
    }

    override suspend fun update(dish: Dish) {
        withContext(Dispatchers.IO) { dishDao.updateDish(dish.toRoomDish()) }
    }
}