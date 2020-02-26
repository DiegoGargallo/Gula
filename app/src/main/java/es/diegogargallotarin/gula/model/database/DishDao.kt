package es.diegogargallotarin.gula.model.database

import androidx.room.*

@Dao
interface  DishDao {

    @Query("SELECT * FROM Dish")
    fun getAll(): List<Dish>

    @Query("SELECT * FROM Dish WHERE name = :name")
    fun findByName(name: String): Dish

    @Query("SELECT COUNT(name) FROM Dish")
    fun dishCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertDishes(dishes: List<Dish>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertContributions(contributions: List<Contribution>)

    @Transaction
    fun insertAll(dishes: List<Dish>, contributions: List<Contribution>) {
        insertDishes(dishes)
        insertContributions(contributions)
    }

    @Update
    fun updateDish(dish: Dish)
}