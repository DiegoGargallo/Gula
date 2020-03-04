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
class FindContributionsByDishNameTest {

    @Mock
    lateinit var gulaRepository: GulaRepository

    lateinit var findContributionsByDishName: FindContributionsByDishName

    @Before
    fun setUp() {
        findContributionsByDishName = FindContributionsByDishName(gulaRepository)
    }

    @Test
    fun `invoke calls gula repository`() {
        runBlocking {

            val dishes = listOf(mockedContribution.copy(dishId = "Pasta"))
            whenever(gulaRepository.getContributionsByDishName("Pasta")).thenReturn(dishes)

            val result = findContributionsByDishName.invoke("Pasta")

            Assert.assertEquals(dishes, result)
        }
    }
}