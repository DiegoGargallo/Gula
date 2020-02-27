package es.diegogargallotarin.gula

import android.app.Application
import androidx.room.Room
import es.diegogargallotarin.gula.data.database.GulaDatabase


class GulaApp : Application() {

    lateinit var db: GulaDatabase
        private set

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(
            this,
            GulaDatabase::class.java, "restaurant-db"
        ).build()
    }
}