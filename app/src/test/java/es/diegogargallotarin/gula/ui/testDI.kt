package es.diegogargallotarin.gula.ui

import es.diegogargallotarin.data.repository.GulaRepository
import es.diegogargallotarin.data.source.LocalDataSource
import es.diegogargallotarin.data.source.RemoteDataSource
import es.diegogargallotarin.domain.Contribution
import es.diegogargallotarin.domain.Dish
import es.diegogargallotarin.testshared.mockedContribution
import es.diegogargallotarin.testshared.mockedDish
import kotlinx.coroutines.Dispatchers
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module


fun initMockedDi(vararg modules: Module) {
    startKoin {
        modules(listOf(mockedAppModule) + modules)
    }
}

private val mockedAppModule = module {
    single<LocalDataSource> { FakeLocalDataSource() }
    single<RemoteDataSource> { FakeRemoteDataSource() }
    single { Dispatchers.Unconfined }
}

val defaultFakeDishes = listOf(
    mockedDish.copy("Pizza"),
    mockedDish.copy("Pasta"),
    mockedDish.copy("Nachos"),
    mockedDish.copy("Hamburguesa")
)

val defaultFakeContributions = listOf(
    mockedContribution.copy(1),
    mockedContribution.copy(2),
    mockedContribution.copy(3),
    mockedContribution.copy(4)
)

class FakeLocalDataSource : LocalDataSource {

    var dishes: List<Dish> = emptyList()
    var contributions: List<Contribution> = emptyList()

    override suspend fun isEmpty() = dishes.isEmpty()

    override suspend fun saveDishes(dishes: List<Dish>) {
        this.dishes = dishes
    }

    override suspend fun saveContributions(contributions: List<Contribution>) {
        this.contributions = contributions
    }

    override suspend fun getAllDishes(): List<Dish> = dishes

    override suspend fun findByName(name: String): Dish  = dishes.first { it.name == name }

    override suspend fun isDishContributionsEmpty(name: String): Boolean {
        return this.contributions.any { it.dishId == name }
    }

    override suspend fun getContributionsByDishName(name: String): List<Contribution> {
        return this.contributions.distinctBy { it.dishId == name }
    }

    override suspend fun update(dish: Dish) {
        dishes = dishes.filterNot { it.name == dish.name } + dish
    }
}

class FakeRemoteDataSource : RemoteDataSource {

    var dishes = defaultFakeDishes
    var contributions = defaultFakeDishes

    override suspend fun getAllDishes(): List<Dish>  = dishes

    override suspend fun getContributionsByDishName(dishName: String): List<Contribution> = defaultFakeContributions
}