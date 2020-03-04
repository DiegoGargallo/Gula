package es.diegogargallotarin.data.repository

import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import es.diegogargallotarin.data.source.LocalDataSource
import es.diegogargallotarin.data.source.RemoteDataSource
import es.diegogargallotarin.domain.Contribution
import es.diegogargallotarin.domain.Dish
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class GulaRepositoryTest {

    @Mock
    lateinit var localDataSource: LocalDataSource

    @Mock
    lateinit var remoteDataSource: RemoteDataSource

    lateinit var gulaRepository: GulaRepository

    @Before
    fun setUp() {
        gulaRepository =
            GulaRepository(localDataSource, remoteDataSource)
    }

    @Test
    fun `getAllDishes gets from local data source first`() {
        runBlocking {

            val localDishes = listOf(mockedDish.copy("1"))
            whenever(localDataSource.isEmpty()).thenReturn(false)
            whenever(localDataSource.getAllDishes()).thenReturn(localDishes)

            val result = gulaRepository.getAllDishes()

            Assert.assertEquals(localDishes, result)
        }
    }

    @Test
    fun `getAllDishes saves remote data to local`() {
        runBlocking {

            val remoteDishes = listOf(mockedDish.copy("2"))
            whenever(localDataSource.isEmpty()).thenReturn(true)
            whenever(remoteDataSource.getAllDishes()).thenReturn(remoteDishes)

            gulaRepository.getAllDishes()

            verify(localDataSource).saveDishes(remoteDishes)
        }
    }

    @Test
    fun `findById calls local data source`() {
        runBlocking {

            val dish = mockedDish.copy(name = "5")
            whenever(localDataSource.findByName("5")).thenReturn(dish)

            val result = gulaRepository.findByName("5")

            Assert.assertEquals(dish, result)
        }
    }

    @Test
    fun `getContributionsByDishName gets from local data source first`() {
        runBlocking {

            val localContributions = listOf(mockedContribution.copy(2))
            whenever(localDataSource.isDishContributionsEmpty("5")).thenReturn(false)
            whenever(localDataSource.getContributionsByDishName("5")).thenReturn(localContributions)

            val result = gulaRepository.getContributionsByDishName("5")

            Assert.assertEquals(localContributions, result)
        }
    }

    @Test
    fun `getContributionsByDishName saves remote data to local`() {
        runBlocking {

            val remoteContributions = listOf(mockedContribution.copy(2))
            whenever(localDataSource.isDishContributionsEmpty("5")).thenReturn(true)
            whenever(remoteDataSource.getContributionsByDishName("5")).thenReturn(remoteContributions)

            gulaRepository.getContributionsByDishName("5")

            verify(localDataSource).saveContributions(remoteContributions)
        }
    }

    @Test
    fun `update updates local data source`() {
        runBlocking {

            val dish = mockedDish.copy(name = "1")

            gulaRepository.update(dish)

            verify(localDataSource).update(dish)
        }
    }

    private val mockedDish = Dish(
        "Pasta",
        "Pasta dish",
        "https://upload.wikimedia.org/wikipedia/commons/f/fc/Strozzapreti_Pasta.JPG",
        true
    )

    private val mockedContribution = Contribution(
        0,
        "Pasta",
        "https://upload.wikimedia.org/wikipedia/commons/f/fc/Strozzapreti_Pasta.JPG",
        "User 1",
        "Restaurant 1"
    )
}