package es.diegogargallotarin.gula.model.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(entity = Dish::class,
    parentColumns = arrayOf("name"),
    childColumns = arrayOf("dishId"),
    onDelete = ForeignKey.CASCADE)]
)
data class Contribution(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var dishId: String? = null,
    var photo: String? = null,
    var user: String? = null,
    var restaurant: String? = null
)
