package es.diegogargallotarin.gula.model.database

import androidx.room.*

@Dao
interface  DishDao {

    @Query("SELECT * FROM Dish")
    fun getAll(): List<Dish>

    @Query("SELECT * FROM Dish WHERE id = :id")
    fun findById(id: Int): Dish

    @Query("SELECT COUNT(id) FROM Dish")
    fun dishCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertDishes(restaurants: List<Dish>)

    @Update
    fun updateDish(dish: Dish)
}