package es.diegogargallotarin.gula

import android.app.Application
import androidx.room.Room
import es.diegogargallotarin.gula.model.database.GulaDatabase


class GulaApp : Application() {

    lateinit var dbRestaurants: GulaDatabase
        private set

    override fun onCreate() {
        super.onCreate()

        dbRestaurants = Room.databaseBuilder(
            this,
            GulaDatabase::class.java, "restaurant-db"
        ).build()
    }
}