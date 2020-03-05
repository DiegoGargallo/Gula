package es.diegogargallotarin.gula.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import es.diegogargallotarin.data.repository.GulaRepository
import es.diegogargallotarin.data.source.LocalDataSource
import es.diegogargallotarin.domain.Contribution
import es.diegogargallotarin.gula.data.database.Dish
import es.diegogargallotarin.gula.data.toRoomDish
import es.diegogargallotarin.gula.ui.FakeLocalDataSource
import es.diegogargallotarin.gula.ui.defaultFakeDishes
import es.diegogargallotarin.gula.ui.initMockedDi
import es.diegogargallotarin.testshared.mockedDish
import es.diegogargallotarin.usecases.FindContributionsByDishName
import es.diegogargallotarin.usecases.FindDishByName
import es.diegogargallotarin.usecases.ToggleDishFavorite
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.get
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class DetailIntegrationTests : AutoCloseKoinTest() {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var dishObserver: Observer<Dish>

    @Mock
    lateinit var contributionsObserver: Observer<List<Contribution>>

    @Mock
    lateinit var favoriteObserver: Observer<Boolean>

    private lateinit var vm: DetailViewModel
    private lateinit var localDataSource: FakeLocalDataSource

    @Before
    fun setUp() {
        val vmModule = module {
            factory { (name: String) -> DetailViewModel(name, get(), get(), get(), get()) }
            factory { FindDishByName(get()) }
            factory { FindContributionsByDishName(get()) }
            factory { ToggleDishFavorite(get()) }
            factory { GulaRepository(get(),get()) }
        }

        initMockedDi(vmModule)
        vm = get { parametersOf("Pizza") }

        localDataSource = get<LocalDataSource>() as FakeLocalDataSource
        localDataSource.dishes = defaultFakeDishes
    }

    @Test
    fun `observing LiveData finds the dish`() {
        vm.dish.observeForever(dishObserver)

        vm.refresh()

        verify(dishObserver).onChanged(mockedDish.copy("Pizza").toRoomDish())
    }

    @Test
    fun `favorite is updated in local data source`() {
        vm.favorite.observeForever(favoriteObserver)

        vm.onFavoriteClicked()

        runBlocking {
            Assert.assertTrue(localDataSource.findByName("Pizza").favorite)
        }
    }
}