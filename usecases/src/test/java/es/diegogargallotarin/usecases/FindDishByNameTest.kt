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
class FindDishByNameTest {

    @Mock
    lateinit var gulaRepository: GulaRepository

    lateinit var findDishByName: FindDishByName

    @Before
    fun setUp() {
        findDishByName = FindDishByName(gulaRepository)
    }

    @Test
    fun `invoke calls gula repository`() {
        runBlocking {

            val movie = mockedDish.copy(name = "Pasta")
            whenever(gulaRepository.findByName("Pasta")).thenReturn(movie)

            val result = findDishByName.invoke("Pasta")

            Assert.assertEquals(movie, result)
        }
    }
}