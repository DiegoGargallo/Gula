package es.diegogargallotarin.gula.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import es.diegogargallotarin.domain.Contribution
import es.diegogargallotarin.gula.data.database.Dish
import es.diegogargallotarin.gula.data.toRoomDish
import es.diegogargallotarin.testshared.mockedContribution
import es.diegogargallotarin.testshared.mockedDish
import es.diegogargallotarin.usecases.FindContributionsByDishName
import es.diegogargallotarin.usecases.FindDishByName
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
class DetailViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var findDishByName: FindDishByName

    @Mock
    lateinit var findContributionsByDishName: FindContributionsByDishName

    @Mock
    lateinit var toggleDishFavorite: ToggleDishFavorite

    @Mock
    lateinit var dishObserver: Observer<Dish>

    @Mock
    lateinit var contributionsObserver: Observer<List<Contribution>>


    private lateinit var vm: DetailViewModel

    @Before
    fun setUp() {
        vm = DetailViewModel("Pasta", findDishByName, findContributionsByDishName, toggleDishFavorite, Dispatchers.Unconfined)
    }

    @Test
    fun `observing LiveData finds the dish`() {

        runBlocking {
            val dish = mockedDish.copy(name = "Pasta")
            val contributions =  listOf(mockedContribution.copy(1))
            whenever(findContributionsByDishName.invoke("Pasta")).thenReturn(contributions)
            whenever(findDishByName.invoke("Pasta")).thenReturn(dish)

            vm.refresh()

            vm.dish.observeForever(dishObserver)

            verify(dishObserver).onChanged(dish.toRoomDish())
        }
    }
    @Test
    fun `observing LiveData finds the contributions`() {

        runBlocking {
            val dish = mockedDish.copy(name = "Pasta")
            val contributions =  listOf(mockedContribution.copy(1))
            whenever(findContributionsByDishName.invoke("Pasta")).thenReturn(contributions)
            whenever(findDishByName.invoke("Pasta")).thenReturn(dish)

            vm.refresh()

            vm.contributions.observeForever(contributionsObserver)

            verify(contributionsObserver).onChanged(contributions)
        }
    }

    @Test
    fun `when favorite clicked, the toggleDishFavorite use case is invoked`() {
        runBlocking {
            val dish = mockedDish.copy(name = "Pasta")
            val contributions =  listOf(mockedContribution.copy(1))
            whenever(findDishByName.invoke("Pasta")).thenReturn(dish)
            whenever(findContributionsByDishName.invoke("Pasta")).thenReturn(contributions)
            whenever(toggleDishFavorite.invoke(dish)).thenReturn(dish.copy(favorite = !dish.favorite))

            vm.dish.observeForever(dishObserver)

            vm.refresh()
            vm.onFavoriteClicked()

            verify(toggleDishFavorite).invoke(dish)
        }
    }
}