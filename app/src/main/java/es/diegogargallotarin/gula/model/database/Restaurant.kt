package es.diegogargallotarin.gula.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.GeoPoint

@Entity
data class Restaurant(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val location: GeoPoint
)