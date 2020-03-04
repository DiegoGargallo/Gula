package es.diegogargallotarin.usecases

import com.nhaarman.mockitokotlin2.verify
import es.diegogargallotarin.data.repository.GulaRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class ToggleDishFavoriteTest {

    @Mock
    lateinit var gulaRepository: GulaRepository

    lateinit var toggleDishFavorite: ToggleDishFavorite

    @Before
    fun setUp() {
        toggleDishFavorite = ToggleDishFavorite(gulaRepository)
    }

    @Test
    fun `invoke calls gula repository`() {
        runBlocking {

            val dish = mockedDish.copy(name = "Pasta")

            val result = toggleDishFavorite.invoke(dish)

            verify(gulaRepository).update(result)
        }
    }

    @Test
    fun `favorite dish becomes unfavorite`() {
        runBlocking {

            val dish = mockedDish.copy(favorite = true)

            val result = toggleDishFavorite.invoke(dish)

            Assert.assertFalse(result.favorite)
        }
    }

    @Test
    fun `unfavorite dish becomes favorite`() {
        runBlocking {

            val dish = mockedDish.copy(favorite = false)

            val result = toggleDishFavorite.invoke(dish)

            Assert.assertTrue(result.favorite)
        }
    }
}