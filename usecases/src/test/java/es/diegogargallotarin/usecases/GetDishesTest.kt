package es.diegogargallotarin.usecases

import com.nhaarman.mockitokotlin2.whenever
import es.diegogargallotarin.data.repository.GulaRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class GetDishesTest {

    @Mock
    lateinit var gulaRepository: GulaRepository

    lateinit var getDishes: GetDishes

    @Before
    fun setUp() {
        getDishes = GetDishes(gulaRepository)
    }

    @Test
    fun `invoke calls gula repository`() {
        runBlocking {

            val movies = listOf(mockedDish.copy(name = "Pasta"))
            whenever(gulaRepository.getAllDishes()).thenReturn(movies)

            val result = getDishes.invoke()

            Assert.assertEquals(movies, result)
        }
    }
}