package es.diegogargallotarin.gula.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import es.diegogargallotarin.data.source.LocalDataSource
import es.diegogargallotarin.domain.Dish
import es.diegogargallotarin.gula.ui.FakeLocalDataSource
import es.diegogargallotarin.gula.ui.defaultFakeDishes
import es.diegogargallotarin.gula.ui.initMockedDi
import es.diegogargallotarin.testshared.mockedDish
import es.diegogargallotarin.usecases.GetDishes
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.get
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class MainIntegrationTests : AutoCloseKoinTest() {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var dishesObserver: Observer<List<Dish>>

    @Mock
    lateinit var loadingObserver: Observer<Boolean>

    private lateinit var vm: MainViewModel

    @Before
    fun setUp() {
        val vmModule = module {
            factory { MainViewModel(get(), get()) }
            factory { GetDishes(get()) }
        }

        initMockedDi(vmModule)
        vm = get()
    }

    @Test
    fun `data is loaded from server when local source is empty`() {
        vm.dishes.observeForever(dishesObserver)

        verify(dishesObserver).onChanged(defaultFakeDishes)
    }

    @Test
    fun `data is loaded from local source when available`() {
        val fakeLocaldishes = listOf(mockedDish.copy("Pasta"), mockedDish.copy("Pizza"))
        val localDataSource = get<LocalDataSource>() as FakeLocalDataSource
        localDataSource.dishes = fakeLocaldishes
        vm.dishes.observeForever(dishesObserver)

        vm.refresh()

        verify(dishesObserver).onChanged(fakeLocaldishes)
    }
}