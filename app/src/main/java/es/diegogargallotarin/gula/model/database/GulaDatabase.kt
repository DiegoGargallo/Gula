package es.diegogargallotarin.gula.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Restaurant::class, Dish::class, Contribution::class], version = 1)
@TypeConverters(CustomTypeConverters::class)
abstract class GulaDatabase : RoomDatabase() {

    abstract fun restaurantDao(): RestaurantDao

    abstract fun dishDao(): DishDao

    abstract fun contributionDao(): ContributionDao
}