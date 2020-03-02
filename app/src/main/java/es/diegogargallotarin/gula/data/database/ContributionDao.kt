package es.diegogargallotarin.gula.data.database

import androidx.room.*

@Dao
interface  ContributionDao {

    @Query("SELECT * FROM Contribution")
    fun getAll(): List<Contribution>

    @Query("SELECT * FROM Contribution WHERE id = :id")
    fun findById(id: Int): Contribution

    @Query("SELECT * FROM Contribution WHERE dishId = :name")
    fun findContributionsByDishName(name: String): List<Contribution>

    @Query("SELECT COUNT(id) FROM Contribution WHERE dishId = :dishName")
    fun contributionCount(dishName: String): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertContributions(contributions: List<Contribution>)

    @Update
    fun updateContribution(contribution: Contribution)
}