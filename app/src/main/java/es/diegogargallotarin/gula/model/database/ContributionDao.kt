package es.diegogargallotarin.gula.model.database

import androidx.room.*

@Dao
interface  ContributionDao {

    @Query("SELECT * FROM Contribution")
    fun getAll(): List<Contribution>

    @Query("SELECT * FROM Contribution WHERE id = :id")
    fun findById(id: Int): Contribution

    @Query("SELECT COUNT(id) FROM Contribution")
    fun contributionCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertContributions(restaurants: List<Contribution>)

    @Update
    fun updateContribution(contribution: Contribution)
}