package es.diegogargallotarin.gula.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Dish(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var name: String? = null,
    var description: String? = null
)