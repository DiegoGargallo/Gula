package es.diegogargallotarin.gula.data.database

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
    var dishId: String,
    var photo: String,
    var user: String,
    var restaurant: String
)
