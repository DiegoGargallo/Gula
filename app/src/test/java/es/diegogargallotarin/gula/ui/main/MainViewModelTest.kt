package es.diegogargallotarin.gula.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import es.diegogargallotarin.domain.Dish
import es.diegogargallotarin.gula.data.toRoomDish
import es.diegogargallotarin.gula.ui.detail.DetailViewModel
import es.diegogargallotarin.testshared.mockedContribution
import es.diegogargallotarin.testshared.mockedDish
import es.diegogargallotarin.usecases.FindContributionsByDishName
import es.diegogargallotarin.usecases.FindDishByName
import es.diegogargallotarin.usecases.GetDishes
import es.diegogargallotarin.usecases.ToggleDishFavorite
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var getDishes: GetDishes

    @Mock
    lateinit var dishesObserver: Observer<List<Dish>>

    @Mock
    lateinit var loadingObserver: Observer<Boolean>

    private lateinit var vm: MainViewModel

    @Before
    fun setUp() {
        vm = MainViewModel(getDishes, Dispatchers.Unconfined)
    }

    @Test
    fun `after requesting the permission, loading is shown`() {
        runBlocking {
            val dishes = listOf(mockedDish.copy(name = "Pasta"))
            whenever(getDishes.invoke()).thenReturn(dishes)
            vm.loading.observeForever(loadingObserver)

            vm.refresh()

            verify(loadingObserver).onChanged(true)
        }
    }

    @Test
    fun `after requesting the permission, getDishes is called`() {

        runBlocking {
            val dishes = listOf(mockedDish.copy(name = "Pasta"))
            whenever(getDishes.invoke()).thenReturn(dishes)

            vm.dishes.observeForever(dishesObserver)

            vm.refresh()

            verify(dishesObserver).onChanged(dishes)
        }
    }
}