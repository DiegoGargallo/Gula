package es.diegogargallotarin.gula.model.database

import androidx.room.TypeConverter
import com.google.firebase.firestore.GeoPoint
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*


class CustomTypeConverters {
    @TypeConverter
    fun stringToGeoPoint(data: String?): GeoPoint {
        return Gson().fromJson(data, GeoPoint::class.java)
    }

    @TypeConverter
    fun geoPointToString(geoPoint: GeoPoint?): String {
        return Gson().toJson(geoPoint)
    }
}