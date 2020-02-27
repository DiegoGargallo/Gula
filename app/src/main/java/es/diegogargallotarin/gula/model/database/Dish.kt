package es.diegogargallotarin.gula.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Dish(
    @PrimaryKey var name: String,
    var description: String,
    var photo: String,
    var favorite: Boolean
)