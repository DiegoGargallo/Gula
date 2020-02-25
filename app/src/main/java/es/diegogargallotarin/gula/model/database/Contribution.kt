package es.diegogargallotarin.gula.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contribution(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var photo: String? = null,
    var user: String? = null,
    var restaurant: String? = null
)