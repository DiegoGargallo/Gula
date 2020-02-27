package es.diegogargallotarin.gula.data.database

import androidx.room.*

@Dao
interface RestaurantDao {

    @Query("SELECT * FROM Restaurant")
    fun getAll(): List<Restaurant>

    @Query("SELECT * FROM Restaurant WHERE id = :id")
    fun findById(id: Int): Restaurant

    @Query("SELECT COUNT(id) FROM Restaurant")
    fun restaurantCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertRestaurants(restaurants: List<Restaurant>)

    @Update
    fun updateRestaurant(restaurant: Restaurant)
}