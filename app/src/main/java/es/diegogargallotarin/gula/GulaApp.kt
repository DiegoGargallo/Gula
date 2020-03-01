package es.diegogargallotarin.gula

import android.app.Application
import androidx.room.Room
import es.diegogargallotarin.gula.data.database.GulaDatabase
import es.diegogargallotarin.gula.di.DaggerGulaComponent
import es.diegogargallotarin.gula.di.GulaComponent

class GulaApp : Application() {

    lateinit var component: GulaComponent
        private set

    override fun onCreate() {
        super.onCreate()

        component = DaggerGulaComponent
            .factory()
            .create(this)
    }
}